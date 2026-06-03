package cl.duoc.mineria.palas.dto;

import cl.duoc.mineria.palas.model.EstadoPala;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEstadoPalaDTO {

    @NotNull(message = "El nuevo estado operativo de la pala es obligatorio")
    private EstadoPala estadoPala;
}