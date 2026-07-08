package cl.duoc.mineria.palas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrarPalaDTO {

    // ==============================
    // MODELO DE LA PALA
    // ==============================

    @NotBlank(message = "El modelo de la pala es obligatorio")

    @Size(
            min = 3,
            max = 50,
            message = "El modelo debe tener entre 3 y 50 caracteres"
    )

    @Pattern(
            regexp = "^[A-Za-z0-9ÁÉÍÓÚáéíóúÑñ\\- ]+$",
            message = "El modelo solo puede contener letras, números, espacios y guiones"
    )
    private String modelo;


    // ==============================
    // SECTOR
    // ==============================

    @NotBlank(message = "El sector de asignación es obligatorio")

    @Size(
            min = 3,
            max = 50,
            message = "El sector debe tener entre 3 y 50 caracteres"
    )

    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El sector solo puede contener letras y espacios"
    )
    private String sector;

}