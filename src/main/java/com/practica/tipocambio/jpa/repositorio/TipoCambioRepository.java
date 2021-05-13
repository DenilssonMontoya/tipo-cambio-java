package com.practica.tipocambio.jpa.repositorio;

import com.practica.tipocambio.jpa.entidad.TipoCambioEntity;
import com.practica.tipocambio.modelo.TipoCambioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioEntity, Integer> {

    @Query("FROM TipoCambioEntity WHERE monedaOrigen = :monedaOrigen and monedaDestino = :monedaDestino")
    TipoCambioEntity obtenerTipoCambioPorMoneda(@Param("monedaOrigen") String monedaOrigen,
                                                @Param("monedaDestino") String monedaDestino);

    @Transactional
    @Modifying
    @Query("UPDATE TipoCambioEntity c set c.factor= :factor WHERE c.monedaOrigen = :monedaOrigen and c.monedaDestino = :monedaDestino")
    Integer actualizarTipoCambioPorMoneda(@Param("monedaOrigen") String monedaOrigen,
                                                @Param("monedaDestino") String monedaDestino,
                                                @Param("factor") Float nuevoFactor);

}
