package ma.valueit.testingplatform.core.service;

import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessException;
import ma.valueit.testingplatform.core.errorhandling.businessexception.InvalidArgumentException;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BasicServiceImpl<T extends CustomAuditable<String, ID, LocalDate>, ID extends Serializable> implements BasicService<T, ID> {
    protected final static Logger logger = LoggerFactory.getLogger(BasicServiceImpl.class);

    @Override
    @Transactional
    public T save(T entity) {
        if (entity == null) {
            throw new InvalidArgumentException(CommonErrorCode.ENTITY_IS_MISSING);
        }

        return getRepository().save(entity);
    }

    @Override
    @Transactional
    public T saveAndFlush(T entity) {
        if (entity == null) {
            throw new InvalidArgumentException(CommonErrorCode.ENTITY_IS_MISSING);
        }

        return getRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public List<T> save(Iterable<T> entities) {
        List<T> result = new ArrayList<T>();

        if (entities == null) {
            return result;
        }

        entities.forEach(entity -> result.add(save(entity)));

        return result;
    }

    @Override
    @Transactional
    public List<T> saveAndFlush(Iterable<T> entities) {
        List<T> result = new ArrayList<T>();

        if (entities == null) {
            return result;
        }

        entities.forEach(entity -> result.add(save(entity)));

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        if (id == null) {
            throw new InvalidArgumentException(CommonErrorCode.ID_IS_MISSING);
        }

        return getRepository().findById(id).orElseThrow(() -> new BusinessException("entity not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public T initializeAndUnProxy(T entity) {
        if (entity == null) {
            throw new InvalidArgumentException(CommonErrorCode.ENTITY_IS_MISSING);
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }

        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(ID id) {
        if (id == null) {
            throw new InvalidArgumentException(CommonErrorCode.ID_IS_MISSING);
        }

        return getRepository().getOne(id);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        if (id == null) {
            throw new InvalidArgumentException(CommonErrorCode.ID_IS_MISSING);
        }

        getRepository().deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll(Iterable<ID> ids) {
        List<T> entities = new ArrayList<>();

        ids.forEach(id -> entities.add(getRepository().getOne(id)));

        return entities;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageRequest) {
        return getRepository().findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getRepository().count();
    }

    @Override
    @Transactional
    public void delete(Iterable<ID> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public List<T> queryEntityListBySql(String sql, Map<String, Object> params) {
        return getRepository().queryEntityListBySql(sql, params);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    @Transactional
    public void delete(List<T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    @Transactional
    public int executeSql(String sql, Map<String, Object> params) {
        return getRepository().executeSql(sql, params);
    }
}
