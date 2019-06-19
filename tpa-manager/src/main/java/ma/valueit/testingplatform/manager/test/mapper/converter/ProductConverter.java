package ma.valueit.testingplatform.manager.test.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ProductDto;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.service.test.CategoryService;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends AbstractListConverter<ProductEntity, ProductDto>  {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @Override
    public ProductEntity convertFrom(ProductDto source) {
        if (source == null) {
            return null;
        }

        ma.valueit.testingplatform.model.product.ProductEntity target = null;
        if (source.getId() != null) {
            target = productService.findById(source.getId());
        } else {
            target = new ProductEntity();
        }


        target.setId(source.getId());
        target.setDesignation(source.getDesignation());
        target.setPrix(source.getPrix());
        target.setCategory(categoryService.getById(source.getCategory().getId()));// on recupere l id du category

        return target;
    }

    @Override
    public ProductDto convertTo(ProductEntity source) {
        if (source == null) {
            return null;
        }

        ProductDto target = new ProductDto();

        if (source.getCreatedDate() != null && source.getCreatedDate().isPresent()) {
            target.setCreatedDate(source.getCreatedDate().get());
        }

        if (source.getLastModifiedBy() != null && source.getLastModifiedBy().isPresent()) {
            target.setLastModifiedBy(source.getLastModifiedBy().get());
        }

        if (source.getLastModifiedDate() != null && source.getLastModifiedDate().isPresent()) {
            target.setLastModifiedDate(source.getLastModifiedDate().get());
        }

        if (source.getCreatedBy() != null && source.getCreatedBy().isPresent()) {
            target.setCreatedBy(source.getCreatedBy().get());
        }

        target.setId(source.getId());
        target.setDesignation(source.getDesignation());
        target.setPrix(source.getPrix());
        target.setCategory(new CategoryConverter().convertTo(source.getCategory()));

        return target;
    }
}
