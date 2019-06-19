package ma.valueit.testingplatform.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BasicRepository<Entity, ID extends Serializable> extends JpaRepository<Entity, ID> {
    int executeSql(String sql, Map<String, Object> params);

    List<Entity> queryEntityListBySql(String sql, Map<String, Object> params);

    EntityManager getEntityManager();
}
