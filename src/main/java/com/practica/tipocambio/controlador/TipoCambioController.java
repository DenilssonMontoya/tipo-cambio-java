package com.practica.tipocambio.controlador;

import com.practica.tipocambio.modelo.TipoCambioDTO;
import com.practica.tipocambio.modelo.TipoCambioNuevoDTO;
import com.practica.tipocambio.negocio.TipoCambioService;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.awt.*;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @GetMapping(value = "/tipocambio/{origen}/{destino}/{monto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<TipoCambioDTO> obtenerTipoDeCambio(
            @PathVariable("origen") String monedaOrigen,
            @PathVariable("destino") String monedaDestino,
            @PathVariable("monto") Float monto){

        return tipoCambioService.obtenerTipoCambio(monedaOrigen,monedaDestino,monto);
    }

    @PutMapping(value = "/tipocambio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Completable actualizarTipoDeCambio(@RequestBody TipoCambioNuevoDTO tipoCambioNuevoDTO){

        return tipoCambioService.actualizarTipoCambio( tipoCambioNuevoDTO.getMonedaOrigen(),
                tipoCambioNuevoDTO.getMonedaDestino(),tipoCambioNuevoDTO.getFactor());
    }
    @PostMapping(value = "/tipocambio", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Completable registrarTipoDeCambio(@RequestBody TipoCambioNuevoDTO tipoCambioNuevoDTO){

        return tipoCambioService.registrarTipoCambio(tipoCambioNuevoDTO.getMonedaOrigen(),
                tipoCambioNuevoDTO.getMonedaDestino(),tipoCambioNuevoDTO.getFactor());
    }


}
