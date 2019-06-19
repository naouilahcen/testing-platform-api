package ma.valueit.testingplatform.core.repository.user;

import ma.valueit.testingplatform.core.model.entity.user.AuthorityCategoryEntity;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;

import java.util.List;

/**
 * Created by yelansari on 2/22/18.
 */
public interface AuthorityRepository extends BasicRepository<AuthorityEntity, Integer> {
    List<AuthorityEntity> findByCategory(AuthorityCategoryEntity category);
}
