package ma.valueit.testingplatform.core.model.auditing;

import com.fasterxml.jackson.core.JsonProcessingException;

import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.model.entity.auditing.HistoryEntity;
import ma.valueit.testingplatform.core.utils.BeanUtil;
import ma.valueit.testingplatform.core.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * Created by yelansari on 5/2/18.
 */
public class BasicEntityListener<T extends CustomAuditable> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicEntityListener.class);

    @PrePersist
    public void prePersist(T target) {
        this.perform(target, Action.INSERTED);
    }

    @PreUpdate
    public void preUpdate(T target) {
        this.perform(target, Action.UPDATED);
    }

    @PreRemove
    public void preRemove(T target) {
        this.perform(target, Action.DELETED);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public static <T> void perform(T target, Action action) {
        if (target == null) {
            return;
        }

        try {
            EntityManager em = BeanUtil.getBean(EntityManager.class);

            String json;

            try {
                json = JSONUtils.toJSON(target);
            } catch (JsonProcessingException e) {
                json = target.toString();
            }

            HistoryEntity history = new HistoryEntity(target.getClass().getName(), json, action);

            em.persist(history);
        } catch (BeansException e) {
            LOGGER.error(String.format("Audit %s failed : %s", action, target), e);
        }
    }
}
