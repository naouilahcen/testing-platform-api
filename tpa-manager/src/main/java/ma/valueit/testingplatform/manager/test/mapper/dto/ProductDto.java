package ma.valueit.testingplatform.manager.test.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.model.category.CategoryEntity;

public class ProductDto extends DtoWithID<Integer> {

    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    private String designation;
    @Getter @Setter
    private Double prix;
    @Getter @Setter
    private CategoryDto category;
}