package dao.Exception;
/**
 * @Author Chouaib
 * @Date 27-11-2021
 * @Project : Avaj-launcher-42
 */
public class ParsingException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
