package classes.controller.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(Object o) {
        super(o.getClass().getName()+" non trovato");
    }
}
