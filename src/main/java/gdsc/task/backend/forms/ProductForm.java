package gdsc.task.backend.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gdsc.task.backend.constants.message.ValidationMessage;
import gdsc.task.backend.validations.annotations.groups.NotBlankGroup;
import gdsc.task.backend.validations.annotations.groups.NotEmptyGroup;
import gdsc.task.backend.validations.annotations.groups.NotNullGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductForm {
    private Object ValidationMessages;
//    @NotNull(message = ValidationMessage.PRODUCT_NAME_NULL, groups = NotNullGroup.class)
//    @NotEmpty(message = ValidationMessage.PRODUCT_NAME_EMPTY, groups = NotEmptyGroup.class)
//    @NotBlank(message = ValidationMessage.PRODUCT_NAME_BLANK, groups = NotBlankGroup.class)
    private String name;
//    @NotNull(message = ValidationMessage.PRODUCT_PRICE_NULL, groups = NotNullGroup.class)
//    @NotEmpty(message = ValidationMessage.PRODUCT_PRICE_EMPTY, groups = NotEmptyGroup.class)
//    @NotBlank(message = ValidationMessage.PRODUCT_PRICE_BLANK, groups = NotBlankGroup.class)
    private Long price;
}
