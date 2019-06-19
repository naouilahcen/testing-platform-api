package ma.valueit.testingplatform.manager.test.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.model.product.ProductEntity;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends DtoWithID<Integer> {
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    private String nomCategory;
    @Getter @Setter
    List<ProductDto> products=new ArrayList<>();

}
