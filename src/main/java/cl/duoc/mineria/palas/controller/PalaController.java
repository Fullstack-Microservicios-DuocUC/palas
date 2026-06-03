package cl.duoc.mineria.palas.controller;

import cl.duoc.mineria.palas.dto.ActualizarEstadoPalaDTO;
import cl.duoc.mineria.palas.dto.RegistrarPalaDTO;
import cl.duoc.mineria.palas.model.Pala;
import cl.duoc.mineria.palas.service.PalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/palas")
@RequiredArgsConstructor
public class PalaController {

    private final PalaService palaService;

    @PostMapping("/registrar")
    public ResponseEntity<Pala> registrar(@Valid @RequestBody RegistrarPalaDTO dto) {
        return new ResponseEntity<>(palaService.registrarPala(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pala>> listarTodas() {
        return ResponseEntity.ok(palaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pala> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(palaService.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pala> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoPalaDTO dto) {
        return ResponseEntity.ok(palaService.actualizarEstado(id, dto));
    }

    // Consumido vía WebClient por Mantención y Ciclos de Transporte
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(palaService.existePala(id));
    }
}