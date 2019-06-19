package ma.valueit.testingplatform.core.model.entity.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by valueit-mac-yelansari on 3/23/18.
 */
@Entity
@EntityListeners(ConfigurationEntityListener.class)
@Table(name = "configurations")
@ToString()
public class ConfigurationEntity extends CustomAuditable<String, Integer, LocalDate> implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private ConfigurationEnum key;

    @Column(name = "valeur")
    @Getter @Setter
    private String value;
}
