package com.conciertos.boletos;

import jakarta.persistence.*;

@Entity
public class Boleto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asiento;
    private Long conciertoId; // <--- ESTO separa a los artistas

    // Constructor vacío
    public Boleto() {}

    // Getters y Setters
    public Long getId() { return id; }
    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }
    public Long getConciertoId() { return conciertoId; }
    public void setConciertoId(Long conciertoId) { this.conciertoId = conciertoId; }
}