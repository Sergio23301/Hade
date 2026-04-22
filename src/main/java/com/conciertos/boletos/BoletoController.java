package com.conciertos.boletos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/boletos")
@CrossOrigin(origins = "*") 
public class BoletoController {

    @Autowired
    private BoletoRepository boletoRepository;

    /**
     * 1. Obtiene la lista de asientos ocupados para un concierto específico.
     * Ruta: GET /api/boletos/ocupados/{conciertoId}
     */
    @GetMapping("/ocupados/{conciertoId}")
    public List<String> obtenerOcupados(@PathVariable Long conciertoId) {
        // Buscamos en la base de datos solo los boletos que coincidan con el ID del concierto
        return boletoRepository.findAsientosByConciertoId(conciertoId);
    }

    /**
     * 2. Procesa la compra de un asiento vinculándolo a un concierto.
     * Ruta: POST /api/boletos/comprar
     */
    @PostMapping("/comprar")
    public Map<String, String> comprar(@RequestBody Boleto pedido) {
        Map<String, String> respuesta = new HashMap<>();
        
        // Validación de seguridad: Verificar que el objeto no llegue nulo
        if (pedido.getAsiento() == null || pedido.getConciertoId() == null) {
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", "Datos incompletos. Se requiere asiento e ID de concierto.");
            return respuesta;
        }

        // VALIDACIÓN CLAVE: Verificar si el asiento ya existe PARA ESE CONCIERTO
        boolean yaExiste = boletoRepository.existsByAsientoAndConciertoId(
            pedido.getAsiento(), 
            pedido.getConciertoId()
        );

        if (yaExiste) {
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", "El asiento " + pedido.getAsiento() + " ya está reservado para este evento.");
            return respuesta;
        }

        // Si está libre, lo guardamos en la base de datos
        try {
            boletoRepository.save(pedido);
            respuesta.put("estado", "EXITO");
            respuesta.put("mensaje", "¡Asiento " + pedido.getAsiento() + " comprado con éxito!");
        } catch (Exception e) {
            respuesta.put("estado", "ERROR");
            respuesta.put("mensaje", "Error al guardar en la base de datos: " + e.getMessage());
        }

        return respuesta;
    }
}