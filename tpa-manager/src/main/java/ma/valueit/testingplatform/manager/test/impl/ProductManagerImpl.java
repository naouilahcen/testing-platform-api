package ma.valueit.testingplatform.manager.test.impl;

import ma.valueit.testingplatform.core.manger.impl.CrudManagerImpl;
import ma.valueit.testingplatform.manager.test.ExampleManager;
import ma.valueit.testingplatform.manager.test.ProductManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.ProductConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ProductDto;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerImpl extends CrudManagerImpl<ProductDto, Integer, ProductEntity, ProductService, ProductConverter> implements ProductManager {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductService getService() {
        return productService;
    }

    @Override
    public ProductConverter getConverter() {
        return productConverter;
    }
}
