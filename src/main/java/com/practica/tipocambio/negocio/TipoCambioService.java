package com.practica.tipocambio.negocio;

import com.practica.tipocambio.modelo.TipoCambioDTO;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

@Service
public interface TipoCambioService {

    public Single<TipoCambioDTO> obtenerTipoCambio(String monedaOrigen, String monedaDestino, Float montoConversion);

    public Completable actualizarTipoCambio(String monedaOrigen, String monedaDestino, Float nuevoMontoConversion);

    public Completable registrarTipoCambio(String monedaOrigen, String monedaDestino, Float montoConversion);

}
