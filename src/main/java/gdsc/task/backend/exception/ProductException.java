package gdsc.task.backend.exception;

public class ProductException extends BaseException{
    private static final long SerialVersionUID=1L;

    public ProductException(String message, int status, Object data, Object[] dynamicData) {
        super(message, status, data, dynamicData);
    }
    public ProductException(String message, int status, Object data){
        super(message, status, data);
    }

    public ProductException(String message, int status) {
        super(message, status);
    }
}
