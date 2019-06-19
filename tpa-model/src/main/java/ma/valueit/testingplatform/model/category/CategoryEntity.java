package ma.valueit.testingplatform.model.category;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.product.ProductEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Category")
public class CategoryEntity extends CustomAuditable<String, Integer, LocalDate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nomCategory;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    @Getter @Setter
    List<ProductEntity> products=new ArrayList<>();

    public CategoryEntity() {
    }

    public CategoryEntity(String nomCategory) {
        this.nomCategory = nomCategory;
    }

    public CategoryEntity(String nomCategory, List<ProductEntity> products) {
        this.nomCategory = nomCategory;
        this.products = products;
    }

}
