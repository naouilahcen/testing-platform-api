package ma.valueit.testingplatform.core.repository.user;

import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by yelansari on 2/22/18.
 */
public interface ProfileRepository extends BasicRepository<ProfileEntity, Integer> {
    @Query("SELECT p FROM ProfileEntity p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<ProfileEntity> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
