package exceptions;

public class InvalidShopTypeException extends Exception {
    public InvalidShopTypeException(String errorMessage) {
        super(errorMessage);
    }
}
