package gdsc.task.backend.exception;

import gdsc.task.backend.dto.ResponseDTO;
import gdsc.task.backend.service.MessageService;
import gdsc.task.backend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageService messageService;
    @ExceptionHandler(value=ProductException.class )
    public ResponseEntity<ResponseDTO<Object>> handleSkillException(ProductException productException){
        ResponseDTO<Object> responseDTO=new ResponseDTO<>();
        responseDTO.setData(productException.getData());
        responseDTO.setHttpStatus(productException.getStatus());
        responseDTO.setMessage(messageService.generateServiceMessage(productException.getMessage(), productException.getDynamicData()));
        responseDTO.setErrorCode(MessageService.generateErrorCode(productException.getMessage()));
        responseDTO.setSuccess(false);
        return new ResponseUtil<Object>().generateControllerResponse(responseDTO);
    }
}
