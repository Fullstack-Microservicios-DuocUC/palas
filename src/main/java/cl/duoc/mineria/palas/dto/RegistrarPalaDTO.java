package cl.duoc.mineria.palas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrarPalaDTO {

    @NotBlank(message = "El modelo de la pala no puede estar vacío")
    @Size(max = 50, message = "El modelo no puede superar los 50 caracteres")
    private String modelo;

    @NotBlank(message = "El sector de asignación inicial es obligatorio")
    @Size(max = 50, message = "El nombre del sector no puede superar los 50 caracteres")
    private String sector;
}