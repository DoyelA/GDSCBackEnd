package gdsc.task.backend.service;

import gdsc.task.backend.dto.ProductDTO;
import gdsc.task.backend.dto.ResponseDTO;
import gdsc.task.backend.forms.ProductForm;

import java.util.Set;

public interface ProductService {
    ResponseDTO<ProductDTO> addProduct(ProductForm productForm);
    ResponseDTO<ProductDTO> viewProduct(Long id);
    ResponseDTO<Set<ProductDTO>> viewProducts();
    ResponseDTO<ProductDTO> updateProduct(Long id, ProductForm productForm);
    ResponseDTO<Void> deleteProduct(Long id);

}
