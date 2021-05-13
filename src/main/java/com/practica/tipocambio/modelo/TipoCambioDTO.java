package com.practica.tipocambio.modelo;

import lombok.Data;

@Data
public class TipoCambioDTO {
    private Float monto;
    private Float montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private Float tipoCambio;

}
