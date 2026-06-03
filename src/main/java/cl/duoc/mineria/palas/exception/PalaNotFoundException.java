package cl.duoc.mineria.palas.exception;

public class PalaNotFoundException extends RuntimeException {
    public PalaNotFoundException(String mensaje) {
        super(mensaje);
    }
}