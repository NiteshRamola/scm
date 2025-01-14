package in.niteshramola.scm.helpers;

public class ResourceNotFoundException extends IllegalArgumentException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
