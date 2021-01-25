package classes.controller.exception;

public class AmbulatorioNotFoundException extends RuntimeException{
    public AmbulatorioNotFoundException(String id) {
        super("Ambulatorio "+id+" non trovato");
    }
}
