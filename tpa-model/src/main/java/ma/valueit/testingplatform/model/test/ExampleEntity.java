package ma.valueit.testingplatform.model.test;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exemples")
public class ExampleEntity extends CustomAuditable<String, Integer, LocalDate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;
}
