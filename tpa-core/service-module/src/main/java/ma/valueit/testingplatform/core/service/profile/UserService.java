package ma.valueit.testingplatform.core.service.profile;

import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by yelansari on 2/23/18.
 */
public interface UserService extends BasicService<UserEntity, Integer> {

    UserEntity findByUsername(String username);

    List<UserEntity> findByProfile(ProfileEntity profileEntity);

    Page<UserEntity> findAllByProfile(ProfileEntity profile, String keyword, PageRequest pageRequest);

    boolean isUsernameAvailable(Integer userId, String username);

    boolean isEmailAvailable(Integer userId, String email);

    void save(UserEntity userEntity, AuthTypeEnum authType);

    UserEntity findByEmail(String email);

    Page<UserEntity> findAllByProfile(ProfileEntity profile, Pageable pageRequest);

    UserEntity findByResetToken(String resetToken);

    UserEntity findByUsernameAndResetCode(String username, Integer code);

    UserEntity findByEmailAndResetCode(String email, Integer code);

    List<UserEntity> findByKeywordAndProfile(String keyword, ProfileEntity profile);

    Page<UserEntity> findAllUsers(PageRequest pageRequest, String firstName, String lastName, String username, String email, ProfileEntity profile, Boolean enabled, Date createdDate);
}
