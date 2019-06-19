package ma.valueit.testingplatform.core.manger.user;


import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.core.manger.mapper.dto.FilterSortDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserFilterFieldsDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserListItemDto;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.UserConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.AuthorityDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.ProfileDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.profile.UserService;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by yelansari on 2/18/18.
 */
public interface UserManager extends CrudManager<UserDto, Integer, UserEntity, UserService, UserConverter>  {
    UserDto createUser(UserDto dto);

    UserDto getUser(Integer id);

    UserDto saveUser(UserDto dto);

    UserDto findByUsername(String username);

    List<ProfileDto> getProfiles();

    Page<ProfileDto> getProfiles(Integer page, Integer pageSize, String keyword);

    void deleteProfile(Integer id);

    List<AuthorityDto> getGroupedAuthorities();

    Integer saveProfile(ProfileDto dto);

    void editProfile(Integer id, ProfileDto dto);

    ProfileDto findOne(Integer id);

    Page<UserDto> findAll(Integer page, Integer pageSize, String keyword);

    UserDto editUser(Integer id, UserDto dto);

    void deleteUser(Integer id);

    boolean checkResetCode(Integer code, String email);

    void resetPassword(Integer id, String password);

    void resetPasswordByToken(String token, String password);

    void resetPasswordByCode(String email, Integer code, String password);

    void recoverPassword(String email);

    void recoverPasswordByCode(String email);

    void enableUser(Integer id);

    void disableUser(Integer id);

    UserDto findUser(Integer id);

    List<AuthorityDto> getProfileAuthorities(Integer id);

    UserDto register(UserDto userDto);

    Page<UserDto> findAllByProfileId(Integer profileId, Integer page, Integer pageSize, String keyword);

    UserDto getCurrentUser();

    void isUserLogged();

    Page<UserListItemDto> findByFilter(FilterSortDto<UserFilterFieldsDto> filterSortDto, Integer page, Integer size);
}