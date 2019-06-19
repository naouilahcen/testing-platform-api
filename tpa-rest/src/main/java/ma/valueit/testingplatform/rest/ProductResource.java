package ma.valueit.testingplatform.rest;


import ma.valueit.testingplatform.core.rest.CrudResource;
import ma.valueit.testingplatform.manager.test.ProductManager;
import ma.valueit.testingplatform.manager.test.mapper.dto.ProductDto;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource extends CrudResource<ProductDto, Integer, ProductManager> {

    @Autowired
    private ProductManager productManager;

    @Override
    protected ProductManager getCrudManager() {
        return productManager;
    }



}
