package dao.Exception;

public class ParsingException extends Exception {
    public ParsingException() {
        super();
    }

    public ParsingException(String error) {
        super(error);
    }

    public ParsingException(Throwable cause) {
        super(cause);
    }

    public ParsingException(String error, Throwable cause) {
        super(error, cause);
    }
}
