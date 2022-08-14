package gdsc.task.backend.service;

public interface MessageService {
    String generateServiceMessage(String messageCode, Object... objects);

    static String generateValidationMessage(String messageCode, Object... objects) {
        return null;
    }

    static String generateErrorCode(String errorCode) {
        return null;
    }

    static String generateExceptionMessage(String exceptionMessage, Object...objects){
        return null;
    }
}
