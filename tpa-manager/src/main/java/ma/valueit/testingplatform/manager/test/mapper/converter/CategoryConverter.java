package ma.valueit.testingplatform.manager.test.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.mapper.converters.ListConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.CategoryDto;
import ma.valueit.testingplatform.manager.test.mapper.dto.ProductDto;
import ma.valueit.testingplatform.model.category.CategoryEntity;
import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.service.test.CategoryService;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter extends AbstractListConverter<CategoryEntity, CategoryDto> {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Override
    public CategoryEntity convertFrom(CategoryDto source) {
        if (source == null) {
            return null;
        }

        ma.valueit.testingplatform.model.category.CategoryEntity target = null;
        if (source.getId() != null) {
            target = categoryService.findById(source.getId());
        } else {
            target = new CategoryEntity();
        }

        target.setId(source.getId());
        target.setNomCategory(source.getNomCategory());
        //target.setProducts(productService.findAll());// on recupere l id du category

        //List<ProductEntity> products = new ArrayList<>();

      /*  for (ProductDto p: source.getProducts()) {
            products.add(productService.findById(p.getId()));
        }*/
        //target.setProducts(products);

        return target;
    }

    @Override
    public CategoryDto convertTo(CategoryEntity source) {
        if (source == null) {
            return null;
        }

        CategoryDto target = new CategoryDto();

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
        target.setNomCategory(source.getNomCategory());

   /*     source.getProducts().forEach(product -> {
            target.getProducts().add(new ProductConverter().convertTo(product));
        });
*/
        return target;
    }
}
