package cl.duoc.mineria.palas.controller;

import cl.duoc.mineria.palas.dto.ActualizarEstadoPalaDTO;
import cl.duoc.mineria.palas.dto.RegistrarPalaDTO;
import cl.duoc.mineria.palas.model.Pala;
import cl.duoc.mineria.palas.service.PalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/palas")
@RequiredArgsConstructor
@Tag(name = "Gestión de Palas", description = "Operaciones para administrar el ciclo de vida de las palas excavadoras.")
public class PalaController {

    private final PalaService palaService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar una nueva pala", description = "Crea una nueva pala excavadora en el sistema con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pala registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Pala> registrar(@Valid @RequestBody RegistrarPalaDTO dto) {
        return new ResponseEntity<>(palaService.registrarPala(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas las palas", description = "Obtiene una lista completa de todas las palas registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de palas obtenida con éxito")
    public ResponseEntity<List<Pala>> listarTodas() {
        return ResponseEntity.ok(palaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una pala por su ID", description = "Busca y devuelve una pala específica a partir de su ID numérico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pala encontrada"),
            @ApiResponse(responseCode = "404", description = "Pala no encontrada con el ID proporcionado")
    })
    public ResponseEntity<Pala> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(palaService.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar el estado de una pala", description = "Modifica el estado operativo de una pala existente (ej. de OPERATIVA a EN_MANTENCION).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la pala actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Pala no encontrada con el ID proporcionado")
    })
    public ResponseEntity<Pala> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoPalaDTO dto) {
        return ResponseEntity.ok(palaService.actualizarEstado(id, dto));
    }

    // Consumido vía WebClient por Mantención y Ciclos de Transporte
    @GetMapping("/existe/{id}")
    @Operation(summary = "Verificar si una pala existe", description = "Endpoint de utilidad para otros microservicios. Devuelve 'true' si la pala con el ID existe, 'false' en caso contrario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verificación realizada, el cuerpo de la respuesta contiene el booleano")
    })
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(palaService.existePala(id));
    }
}