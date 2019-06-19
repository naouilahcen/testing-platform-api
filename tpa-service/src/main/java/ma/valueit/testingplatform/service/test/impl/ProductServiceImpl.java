package ma.valueit.testingplatform.service.test.impl;

import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.repository.test.ProductRepository;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  extends BasicServiceImpl<ProductEntity, Integer> implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public BasicRepository<ProductEntity, Integer> getRepository() {
        return productRepository;
    }
}

