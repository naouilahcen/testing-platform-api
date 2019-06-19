package ma.valueit.testingplatform.core.service.profile.impl;

import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessException;
import ma.valueit.testingplatform.core.errorhandling.businessexception.InvalidArgumentException;
import ma.valueit.testingplatform.core.errorhandling.businessexception.UserNotFoundException;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;
import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.repository.user.UserRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.core.service.exception.UserBusinessErrorCode;
import ma.valueit.testingplatform.core.service.profile.UserService;
import ma.valueit.testingplatform.core.utils.DateUtils;
import ma.valueit.testingplatform.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class UserServiceImpl extends BasicServiceImpl<UserEntity, Integer> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public BasicRepository<UserEntity, Integer> getRepository() {
        return userRepository;
    }

    @Override
    public void save(UserEntity userEntity, AuthTypeEnum authType) {
        userEntity.setAuthType(authType);

        this.save(userEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            throw new InvalidArgumentException(UserBusinessErrorCode.USERNAME_IS_MISSING);
        }

        return userRepository.findByUsername(username);

    }

    @Override
    public List<UserEntity> findByProfile(ProfileEntity profile) {
        if(profile == null) {
            throw new InvalidArgumentException(UserBusinessErrorCode.PROFILE_IS_MISSING);
        }

        return userRepository.findByProfile(profile);
    }

    @Override
    public Page<UserEntity> findAllByProfile(ProfileEntity profile, Pageable pageRequest) {
        if(StringUtils.isEmpty(profile)) {
            throw new InvalidArgumentException(UserErrorCode.PROFILE_IS_MISSING);
        }

        return this.userRepository.findByProfile(profile, pageRequest);
    }

    @Override
    public Page<UserEntity> findAllByProfile(ProfileEntity profile, String keyword, PageRequest pageRequest) {
        if(StringUtils.isEmpty(profile)) {
            throw new InvalidArgumentException(UserErrorCode.PROFILE_IS_MISSING);
        }

        if(StringUtils.isEmpty(keyword)) {
            throw new InvalidArgumentException(CommonErrorCode.KEYWORD_IS_MISSING);
        }

        return this.userRepository.findByProfile(profile, keyword, pageRequest);
    }

    @Override
    public boolean isUsernameAvailable(Integer userId, String username) {
        UserEntity user;

        try {
            user = findByUsername(username);

            if(user == null || (user != null && !StringUtils.isEmpty(userId) && userId.equals(user.getId()))) {
                return true; // gotten same user
            }
        } catch (BusinessException e) {
            return true; // if not exist 'no user with given username' BusinessException will be thrown
        }

        return false;
    }

    @Override
    public boolean isEmailAvailable(Integer userId, String email) {
        UserEntity user;

        try {
            user = findByEmail(email);

            if(user == null || (user != null && !StringUtils.isEmpty(userId) && userId.equals(user.getId()))) {
                return true; // gotten same user
            }
        } catch (BusinessException e) {
            return true; // if not exist 'no user with given username' BusinessException will be thrown
        }

        return false;
    }

    @Override
    public UserEntity findByEmail(String email) {
        if(StringUtils.isEmpty(email)) {
            throw new InvalidArgumentException(UserErrorCode.EMAIL_IS_MISSING);
        }

        return this.userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByResetToken(String resetToken) {
        if(StringUtils.isEmpty(resetToken)) {
            throw new InvalidArgumentException(UserErrorCode.RESET_TOKEN_IS_MISSING);
        }

        return this.userRepository.findByResetToken(resetToken);
    }

    @Override
    public UserEntity findByUsernameAndResetCode(String username, Integer code) {
        if(StringUtils.isEmpty(username)) {
            throw new InvalidArgumentException(UserErrorCode.USERNAME_IS_MISSING);
        }

        if(StringUtils.isEmpty(code)) {
            throw new InvalidArgumentException(UserErrorCode.CODE_IS_MISSING);
        }

        return this.userRepository.findByUsernameAndResetCode(username, code);
    }

    @Override
    public UserEntity findByEmailAndResetCode(String email, Integer code) {
        if(StringUtils.isEmpty(email)) {
            throw new InvalidArgumentException(UserErrorCode.EMAIL_IS_MISSING);
        }

        if(StringUtils.isEmpty(code)) {
            throw new InvalidArgumentException(UserErrorCode.CODE_IS_MISSING);
        }

        return this.userRepository.findByEmailAndResetCode(email, code);
    }

    @Override
    public List<UserEntity> findByKeywordAndProfile(String keyword, ProfileEntity profile) {
        if(StringUtils.isEmpty(keyword)) {
            throw new InvalidArgumentException(CommonErrorCode.KEYWORD_IS_MISSING);
        }

        if(StringUtils.isEmpty(profile)) {
            throw new InvalidArgumentException(UserErrorCode.PROFILE_IS_MISSING);
        }

        return this.userRepository.findByKeywordAndProfile(keyword, profile);
    }

    @Override
    public Page<UserEntity> findAllUsers(PageRequest pageRequest, String firstName, String lastName, String username, String email, ProfileEntity profile, Boolean enabled, Date createdDate) {
        createdDate = DateUtils.getDateMinimum(createdDate);
        Date createdDate2 = DateUtils.getDateMidnight(createdDate);

        return userRepository.findAllUsers(firstName, lastName, username, email, profile, enabled, createdDate, createdDate2, pageRequest);

    }
}
