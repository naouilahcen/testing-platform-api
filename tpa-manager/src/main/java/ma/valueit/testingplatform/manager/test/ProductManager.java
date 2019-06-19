package ma.valueit.testingplatform.manager.test;

import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.ProductConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ProductDto;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.service.test.ProductService;

public interface ProductManager extends CrudManager<ProductDto, Integer, ProductEntity, ProductService, ProductConverter> {
}
