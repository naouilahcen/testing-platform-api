package ma.valueit.testingplatform.core.model.entity.auditing;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.auditing.Action;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by yelansari on 1/28/18.
 */
@Entity
@Table(name = "logs")
public class HistoryEntity {
    private static final long serialVersionUID = -9213672486077901902L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String clazz;

    @Column(columnDefinition = "LONGTEXT")
    @Getter @Setter
    private String json;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Action action;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public HistoryEntity() {

    }

    public HistoryEntity(String clazz, String json, Action action) {
        this.clazz = clazz;
        this.json = json;
        this.action = action;
    }
}
