package ma.valueit.testingplatform.core.repository.user;

import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
public interface UserRepository extends BasicRepository<UserEntity, Integer> {
    List<UserEntity> findByProfile(ProfileEntity profileEntity);

    UserEntity findByUsername(String userName);

    UserEntity findByEmail(String email);

    Page<UserEntity> findByProfile(ProfileEntity profile, Pageable pageRequest);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE " +
            "   u.profile = :profile AND " +
            "   ( " +
            "       u.firstname like %:keyword% OR " +
            "       u.lastname like %:keyword% OR " +
            "       u.username like %:keyword% OR " +
            "       u.mobile like %:keyword% OR " +
            "       u.phone like %:keyword% OR " +
            "       u.email like %:keyword%" +
            "   )")
    Page<UserEntity> findByProfile(@Param("profile") ProfileEntity profile, @Param("keyword") String keyword, Pageable pageRequest);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE " +
            "   u.profile = :profile AND " +
            "   ( " +
            "       u.firstname like %:keyword% OR " +
            "       u.lastname like %:keyword% OR " +
            "       u.username like %:keyword% OR " +
            "       u.mobile like %:keyword% OR " +
            "       u.phone like %:keyword% OR " +
            "       u.email like %:keyword%" +
            "   )")
    List<UserEntity> findByKeywordAndProfile(@Param("keyword") String keyword, @Param("profile") ProfileEntity profile);

    UserEntity findByResetToken(String resetToken);

    UserEntity findByUsernameAndResetCode(String username, Integer resetCode);

    UserEntity findByEmailAndResetCode(String email, Integer code);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE (:profile IS NULL OR u.profile = :profile) " +
            "AND u.firstname LIKE %:firstName% " +
            "AND u.lastname LIKE %:lastName% " +
            "AND u.username LIKE %:username% " +
            "AND u.email LIKE %:email% " +
            "AND (:enabled IS NULL OR u.enabled = :enabled )" +
            "AND (:createdDate IS NULL OR u.createdDate BETWEEN :createdDate AND :createdDate2)")
    Page<UserEntity> findAllUsers(@Param("firstName") String firstName,
                                  @Param("lastName") String lastName,
                                  @Param("username") String username,
                                  @Param("email") String email,
                                  @Param("profile") ProfileEntity profile,
                                  @Param("enabled") Boolean enabled,
                                  @Param("createdDate") Date createdDate,
                                  @Param("createdDate2") Date createdDate2,
                                  Pageable pageRequest);
}

