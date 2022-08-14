package gdsc.task.backend.controller;
import gdsc.task.backend.constants.url.ApiURL;
import gdsc.task.backend.dto.ProductDTO;
import gdsc.task.backend.dto.ResponseDTO;
import gdsc.task.backend.forms.ProductForm;
import gdsc.task.backend.service.ProductService;
import gdsc.task.backend.utils.ResponseUtil;
import gdsc.task.backend.validations.annotations.sequence.ValidationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = ApiURL.PRODUCT_URL)
public class ProductController {

    @Autowired
    public ProductService productService;


    @PostMapping(value="/add")
    public @ResponseBody ResponseEntity<ResponseDTO<ProductDTO>> addProduct(@Validated(value= ValidationSequence.class)
                                                        @RequestBody ProductForm productForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<ProductDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<ProductDTO>().generateControllerResponse(productService.addProduct(productForm));
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<ResponseDTO<ProductDTO>> updateProduct(@PathVariable(value="id") Long id, @Validated(value=ValidationSequence.class) @RequestBody ProductForm productForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<ProductDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<ProductDTO>().generateControllerResponse(productService.updateProduct(id, productForm));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<ProductDTO>> getProductById(@PathVariable(value="id") Long id){
        return new ResponseUtil<ProductDTO>().generateControllerResponse(productService.viewProduct(id));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Set<ProductDTO>>> getAllProducts(){
        return new ResponseUtil<Set<ProductDTO>>().generateControllerResponse(productService.viewProducts());
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteProduct(@PathVariable(value="id") Long id){
        return new ResponseUtil<Void>().generateControllerResponse(productService.deleteProduct(id));
    }
}
