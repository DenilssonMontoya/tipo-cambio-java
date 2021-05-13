package com.practica.tipocambio.negocio.impl;

import com.practica.tipocambio.exceptions.ExistsException;
import com.practica.tipocambio.exceptions.NotFoundException;
import com.practica.tipocambio.jpa.entidad.TipoCambioEntity;
import com.practica.tipocambio.jpa.repositorio.TipoCambioRepository;
import com.practica.tipocambio.modelo.TipoCambioDTO;
import com.practica.tipocambio.negocio.TipoCambioService;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class TipoCambioServiceImpl implements TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Single<TipoCambioDTO> obtenerTipoCambio(String monedaOrigen, String monedaDestino,
                                                   Float montoConversion){
        return Maybe.fromCallable(() ->
                tipoCambioRepository.obtenerTipoCambioPorMoneda(monedaOrigen,monedaDestino))
                .isEmpty()
                .flatMap(empty -> {
                    if(empty){
                        throw new NotFoundException("No existe tipo de cambio");
                    }
                    return Single.fromCallable(() ->
                            tipoCambioRepository.obtenerTipoCambioPorMoneda(monedaOrigen,monedaDestino));
                })
                .flatMap(tipoCambioEntity -> {
                    return Single.just(convertirEntity(tipoCambioEntity))
                            .map(tipoCambioDTO -> {
                                tipoCambioDTO.setMonto(montoConversion);
                                tipoCambioDTO.setMontoTipoCambio(montoConversion * tipoCambioDTO.getTipoCambio());
                                return tipoCambioDTO;
                            });
                });

    };
    @Override
    public Completable actualizarTipoCambio(String monedaOrigen, String monedaDestino, Float nuevoMontoConversion){
        return Completable.fromCallable(() -> {
            Integer resultado =  tipoCambioRepository.actualizarTipoCambioPorMoneda(monedaOrigen,monedaDestino,nuevoMontoConversion);
            if (resultado == 0){
                throw new NotFoundException("No se encontro registro");
            }
            return false;
        });
    }

    public Completable registrarTipoCambio(String monedaOrigen, String monedaDestino, Float montoConversion){
        return
                Maybe.fromCallable(() -> {
                  return tipoCambioRepository.obtenerTipoCambioPorMoneda(monedaOrigen,monedaDestino);
                })
                .isEmpty()
                .flatMapCompletable(empty -> {
                    if(!empty){
                        throw new ExistsException("Registro ya existe");
                    }
                    TipoCambioEntity tipoCambioEntity = new TipoCambioEntity();
                    tipoCambioEntity.setFactor(montoConversion);
                    tipoCambioEntity.setMonedaDestino(monedaDestino);
                    tipoCambioEntity.setMonedaOrigen(monedaOrigen);
                    return Completable.fromCallable(() -> tipoCambioRepository.save(tipoCambioEntity));
                });

    }

    private TipoCambioDTO convertirEntity(TipoCambioEntity tipoCambioEntity){
        TipoCambioDTO tipoCambioDTO = new TipoCambioDTO();
        tipoCambioDTO.setTipoCambio(tipoCambioEntity.getFactor());
        tipoCambioDTO.setMonedaDestino(tipoCambioEntity.getMonedaDestino());
        tipoCambioDTO.setMonedaOrigen(tipoCambioEntity.getMonedaOrigen());
        return tipoCambioDTO;
    }


}
