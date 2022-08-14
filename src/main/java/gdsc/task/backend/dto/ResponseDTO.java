package gdsc.task.backend.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private T data;
    private boolean success;
    private int httpStatus;
    private String message;
    private String errorCode;
    private Long timeStamp=System.currentTimeMillis();
}
