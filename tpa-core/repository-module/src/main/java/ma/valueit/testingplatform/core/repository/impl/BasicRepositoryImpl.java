package ma.valueit.testingplatform.core.repository.impl;

import ma.valueit.testingplatform.core.repository.BasicRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public class BasicRepositoryImpl<ENTITY, ID extends Serializable> extends SimpleJpaRepository<ENTITY, ID> implements BasicRepository<ENTITY, ID> {
    private final EntityManager entityManager;

    private final Class<ENTITY> entityClass;


    public BasicRepositoryImpl(JpaEntityInformation<ENTITY, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityClass = entityInformation.getJavaType();
    }

    public BasicRepositoryImpl(Class<ENTITY> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ENTITY> queryEntityListBySql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql, this.entityClass);
        setParameter(query, params);
        return query.getResultList();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        Query query = this.entityManager.createNativeQuery(sql);
        setParameter(query, params);
        return query.executeUpdate();
    }

    @SuppressWarnings("rawtypes")
    private void setParameter(Query query, Map<String, Object> params) {
        if (params != null) {
            Iterator it = params.entrySet().iterator();

            Map.Entry en = null;

            while (it.hasNext()) {
                en = (Map.Entry) it.next();
                query.setParameter((String) en.getKey(), en.getValue());
            }
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
    
