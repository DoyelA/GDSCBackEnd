package gdsc.task.backend.service.impl;

import gdsc.task.backend.constants.message.ExceptionMessage;
import gdsc.task.backend.constants.message.ServiceMessage;
import gdsc.task.backend.domain.Product;
import gdsc.task.backend.dto.ProductDTO;
import gdsc.task.backend.dto.ResponseDTO;
import gdsc.task.backend.exception.ProductException;
import gdsc.task.backend.forms.ProductForm;
import gdsc.task.backend.repository.ProductRepository;
import gdsc.task.backend.service.ProductService;
import gdsc.task.backend.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseDTO<ProductDTO> addProduct(ProductForm productForm) {
        Product product= new Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product=productRepository.save(product);
        ProductDTO productDTO=new ProductDTO(product.getId(),product.getName(), product.getPrice());
        return new ResponseUtil<ProductDTO>().generateServiceResponse(ServiceMessage.PRODUCT_CREATED_SUCCESSFULLY, true, productDTO, HttpStatus.CREATED.value());
    }

    @Override
    public ResponseDTO<ProductDTO> viewProduct(Long id) {
        Product product=productRepository.getOne(id);
        ProductDTO productDTO= new ProductDTO(product);
        return new ResponseUtil<ProductDTO>().generateServiceResponse(ServiceMessage.PRODUCT_FOUND ,true, productDTO, HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<Set<ProductDTO>> viewProducts() {
        try {
            List<Product> products = productRepository.findAll();
            Set<ProductDTO> productDTOS = new HashSet<>();
            products.forEach(product -> {
                productDTOS.add(new ProductDTO(product.getId(),product.getName(), product.getPrice()));
            });

            return new ResponseUtil<Set<ProductDTO>>().generateServiceResponse(ServiceMessage.PRODUCTS_FOUND, true, productDTOS, HttpStatus.OK.value());
        } catch (Exception e) {
            throw new ProductException(ExceptionMessage.PRODUCT_FETCH_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseDTO<ProductDTO> updateProduct(Long id, ProductForm productForm) {
        Product product = productRepository.getOne(id);
        boolean isUpdateRequired = false;
        if (!productForm.getName().equals(product.getName()) || productForm.getPrice().equals(product.getPrice())) {
            isUpdateRequired = true;
        }
        if (isUpdateRequired = true) {
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            productRepository.save(product);

        }
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        return new ResponseUtil<ProductDTO>().generateServiceResponse(ServiceMessage.PRODUCT_UPDATED, true, productDTO, HttpStatus.CREATED.value());
    }

    @Override
    public ResponseDTO<Void> deleteProduct(Long id) {
        try{
            productRepository.deleteById(id);
            return new ResponseUtil<Void>().generateServiceResponse(ServiceMessage.PRODUCT_DELETED, true, null, HttpStatus.OK.value());
        }catch(Exception e){
            throw new ProductException(ExceptionMessage.PRODUCT_DELETE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
