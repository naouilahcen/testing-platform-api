package ma.valueit.testingplatform.core.model.entity.config;

import ma.valueit.testingplatform.core.model.auditing.Action;
import ma.valueit.testingplatform.core.model.auditing.BasicEntityListener;

import javax.persistence.*;

/**
 * Created by valueit-mac-yelansari on 5/2/18.
 */
public class ConfigurationEntityListener {
    @PrePersist
    public void prePersist(ConfigurationEntity target) {

    }

    @PostPersist
    public void postPersist(ConfigurationEntity target) {
        BasicEntityListener.perform(target, Action.INSERTED);
    }

    @PreUpdate
    public void preUpdate(ConfigurationEntity target) {

    }

    @PostUpdate
    public void postUpdate(ConfigurationEntity target) {
        BasicEntityListener.perform(target, Action.UPDATED);
    }

    @PreRemove
    public void preRemove(ConfigurationEntity target) {

    }

    @PostRemove
    public void postRemove(ConfigurationEntity target) {
        BasicEntityListener.perform(target, Action.DELETED);
    }
}
