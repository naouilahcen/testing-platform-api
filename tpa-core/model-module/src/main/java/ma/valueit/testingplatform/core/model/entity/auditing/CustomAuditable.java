package ma.valueit.testingplatform.core.model.entity.auditing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * Created by valueit-mac-yelansari on 1/28/18.
 * Modified by valueit-mac-yelkarkouri on 2/04/19.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CustomAuditable<USER extends Serializable, ID extends Serializable, T extends TemporalAccessor>
        implements Auditable<USER, ID, T> {
    private static final long serialVersionUID = -6576908989844121447L;

    @JsonIgnore
    @Column(name = "cree_par")
    @Setter
    private USER createdBy;

    @JsonIgnore
    @Column(name = "modifie_par")
    @Setter
    private USER lastModifiedBy;

    @JsonIgnore
    @Column(name = "date_creation")
    @Setter
    private T createdDate;

    @JsonIgnore
    @Column(name = "date_modification")
    @Setter
    private T lastModifiedDate;

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    public Optional<USER> getCreatedBy() {
        if (createdBy != null )
            return Optional.of(createdBy);
        return Optional.empty();
    }

    public Optional<USER> getLastModifiedBy() {
        if (lastModifiedBy != null )
            return Optional.of(lastModifiedBy);
        return Optional.empty();
    }

    public Optional<T> getCreatedDate() {
        if (createdDate != null )
            return Optional.of(createdDate);
        return Optional.empty();
    }

    public Optional<T> getLastModifiedDate() {
        if (lastModifiedDate != null )
            return Optional.of(lastModifiedDate);
        return Optional.empty();
    }

    public abstract void setId(ID id);
}
