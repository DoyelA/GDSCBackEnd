package gdsc.task.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gdsc.task.backend.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;
    private String name;
    private Long price;

    public ProductDTO(Product product){
        this.id=product.getId();
        this.name=product.getName();
        this.price=product.getPrice();
    }
}
