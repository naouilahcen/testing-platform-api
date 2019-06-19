package ma.valueit.testingplatform.core.model.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by yelansari on 2/22/18.
 */
@Entity
@Table(name = "authorities_categories")
@ToString
public class AuthorityCategoryEntity extends CustomAuditable<String, Integer, LocalDate> {
    private static final long serialVersionUID = -9213672486077901902L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String title;
}
