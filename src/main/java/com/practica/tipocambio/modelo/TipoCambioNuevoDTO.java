package com.practica.tipocambio.modelo;

import lombok.Data;

@Data
public class TipoCambioNuevoDTO {

    private String monedaOrigen;
    private String monedaDestino;
    private Float factor;
}
