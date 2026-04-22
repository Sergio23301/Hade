package com.conciertos.boletos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
    
    // Esta es la que usa tu Controller para validar
    boolean existsByAsientoAndConciertoId(String asiento, Long conciertoId);

    // Esta es la que usa tu Controller para listar ocupados
    @Query("SELECT b.asiento FROM Boleto b WHERE b.conciertoId = :conciertoId")
    List<String> findAsientosByConciertoId(Long conciertoId);
}