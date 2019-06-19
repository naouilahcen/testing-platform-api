package ma.valueit.testingplatform.model.attachment;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "attachments")
public class AttachmentEntity extends CustomAuditable<String , Integer , LocalDate> {

    @Id
    @Getter @Setter
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter @Setter
    private String path;

    @Getter
    @Setter
    @Column(name = "file_name")
    private String fileName;

    @Getter
    @Setter
    private String type ;

    @Getter
    @Setter
    private Long size;

    @Getter
    @Setter
    private String content ;



}
