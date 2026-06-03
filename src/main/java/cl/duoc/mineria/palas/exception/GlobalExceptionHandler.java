package cl.duoc.mineria.palas.exception;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        System.out.println("GlobalExceptionHandler (Módulo Palas - 8085) REGISTRADO");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
        System.out.println("[Palas Error] - Datos de payload inválidos");
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validación en los datos de la pala");
        problem.setTitle("Validation Error");
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (e, n) -> e));

        problem.setProperty("errores", errors);
        return problem;
    }

    @ExceptionHandler(PalaNotFoundException.class)
    public ProblemDetail handlePalaNotFound(PalaNotFoundException ex) {
        System.out.println("[Palas Warning] - Pala no encontrada: " + ex.getMessage());
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Shovel not Found");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        System.out.println("CRÍTICO - Error no controlado en Palas: " + ex.getClass().getName());
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en el servidor de Palas");
        problem.setTitle("Internal Server Error");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
