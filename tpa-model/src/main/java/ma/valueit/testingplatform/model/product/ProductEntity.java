package ma.valueit.testingplatform.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.category.CategoryEntity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Products")
public class ProductEntity extends CustomAuditable<String, Integer, LocalDate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String designation;
    @Getter @Setter
    private Double prix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Getter @Setter
    private CategoryEntity category;


    public ProductEntity(String designation, Double prix) {
        this.designation = designation;
        this.prix = prix;
    }

    public ProductEntity(String designation, Double prix, CategoryEntity category) {
        this.designation = designation;
        this.prix = prix;
        this.category = category;
    }

    public ProductEntity() {
    }
}


