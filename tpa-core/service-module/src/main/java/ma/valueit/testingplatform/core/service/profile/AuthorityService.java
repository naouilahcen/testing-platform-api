package ma.valueit.testingplatform.core.service.profile;

import ma.valueit.testingplatform.core.model.entity.user.AuthorityCategoryEntity;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.service.BasicService;

import java.util.List;

/**
 * Created by yelansari on 2/23/18.
 */
public interface AuthorityService extends BasicService<AuthorityEntity, Integer> {
    List<AuthorityEntity> findByCategory(AuthorityCategoryEntity category);
}
