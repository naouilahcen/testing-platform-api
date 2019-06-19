package ma.valueit.testingplatform.core.service.profile;

import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yelansari on 2/23/18.
 */
public interface ProfileService extends BasicService<ProfileEntity, Integer> {
    public Page<ProfileEntity> findByKeyword(String keyword, Pageable pageable);
}
