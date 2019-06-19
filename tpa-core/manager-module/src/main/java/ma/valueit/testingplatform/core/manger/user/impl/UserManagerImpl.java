package ma.valueit.testingplatform.core.manger.user.impl;

import ma.valueit.testingplatform.core.errorhandling.businessexception.*;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;
import ma.valueit.testingplatform.core.manger.exception.AuthenticationActivityManagementErrorCode;
import ma.valueit.testingplatform.core.manger.exception.CommonManagementErrorCode;
import ma.valueit.testingplatform.core.manger.exception.ProfileManagementErrorCode;
import ma.valueit.testingplatform.core.manger.exception.UserManagementErrorCode;
import ma.valueit.testingplatform.core.manger.impl.CrudManagerImpl;
import ma.valueit.testingplatform.core.manger.mapper.converters.FilterSortConverter;
import ma.valueit.testingplatform.core.manger.mapper.dto.FilterSortDto;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.UserListItemConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserFilterFieldsDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserListItemDto;
import ma.valueit.testingplatform.core.manger.user.UserManager;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.AuthorityConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.ProfileConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.UserConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.AuthorityDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.ProfileDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.security.model.JwtUser;
import ma.valueit.testingplatform.core.security.service.JdbcUserDetailsService;
import ma.valueit.testingplatform.core.security.utils.JwtTokenUtil;
import ma.valueit.testingplatform.core.service.exception.MailBusinessErrorCode;
import ma.valueit.testingplatform.core.service.mail.MailService;
import ma.valueit.testingplatform.core.service.mail.model.Mail;
import ma.valueit.testingplatform.core.service.profile.AuthorityService;
import ma.valueit.testingplatform.core.service.profile.ProfileService;
import ma.valueit.testingplatform.core.service.profile.UserService;
import ma.valueit.testingplatform.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by yelansari on 2/18/18.
 */
@Service
public class UserManagerImpl extends CrudManagerImpl<UserDto, Integer, UserEntity, UserService, UserConverter> implements UserManager {

    private UserDto currentUser;

    @Value("${reset-password-url}")
    private String resetPasswordURL;

    @Autowired
    protected JdbcUserDetailsService userDetailsService;

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AuthorityConverter authorityConverter;

    @Autowired
    private ProfileConverter profileConverter;

    @Autowired
    private FilterSortConverter filterSortConverter;

    @Autowired
    private UserListItemConverter userListItemConverter;

    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Override
    public UserDto createUser(UserDto dto) throws BusinessException {
        if (dto == null) {
            throw new InvalidPayloadException();
        }

        if (dto.getAuthType() == null) {
            throw new BusinessException(UserManagementErrorCode.AUTH_TYPE_MISSING);
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(dto.getEmail())) {
            throw new BusinessException(UserErrorCode.EMAIL_IS_MISSING);
        }

        if (org.apache.commons.lang3.StringUtils.isEmpty(dto.getFirstname())) {
            throw new BusinessException(UserManagementErrorCode.FIRSTNAME_IS_MISSING);
        }

        if (org.apache.commons.lang3.StringUtils.isEmpty(dto.getLastname())) {
            throw new BusinessException(UserManagementErrorCode.LASTNAME_IS_MISSING);
        }

        if (org.apache.commons.lang3.StringUtils.isEmpty(dto.getUsername())) {
            throw new BusinessException(UserErrorCode.USERNAME_IS_MISSING);
        }

        // convert to entity in order to save
        UserEntity user = userConverter.convertFrom(dto);

        // encode passsword before saving
        String password = passwordEncoder.encode(dto.getPassword());

        user.setPassword(password);

        user = userService.save(user);

        // update dto
        dto = userConverter.convertTo(user);
        return dto;
    }

    @Override
    public UserDto getUser(Integer id) throws BusinessException {
        if (StringUtils.isEmpty(id)) {
            throw new MissingIdException();
        }

        UserEntity entity = userService.findById(id);

        if (entity == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        UserDto dto = userConverter.convertTo(entity);

        return dto;
    }

    public UserDto saveUser(UserDto dto) {
        if (dto == null) {
            throw new InvalidPayloadException();
        }

        dto.validate();

        if (!userService.isUsernameAvailable(dto.getId(), dto.getUsername())) {
            throw new BusinessException(UserManagementErrorCode.DUPLICATE_USERNAME);
        }

        if (StringUtils.isEmpty(dto.getPassword())) {
            throw new BusinessException(UserManagementErrorCode.PASSWORD_IS_MISSING);
        }

        UserEntity user = userConverter.convertFrom(dto);

        // encode password before saving
        String password = passwordEncoder.encode(dto.getPassword());
        user.setPassword(password);

        userService.save(user, AuthTypeEnum.SIMPLE);

        return userConverter.convertTo(user);
    }

    @Override
    public UserDto editUser(Integer id, UserDto dto) {
        if (StringUtils.isEmpty(id)) {
            throw new MissingIdException();
        }

        if (dto == null) {
            throw new InvalidPayloadException();
        }

        if (dto.getId() == null) {
            throw new BusinessException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        if (!id.equals(dto.getId())) {
            throw new UnAuthorizedException();
        }

        dto.validate();

        if (!userService.isUsernameAvailable(dto.getId(), dto.getUsername())) {
            throw new BusinessException(UserManagementErrorCode.DUPLICATE_USERNAME);
        }

        UserEntity user = userConverter.convertFrom(dto);

        List<AuthorityEntity> excludedAuthorities = authorityService.getAll(dto.getExcludedAuthoritiesIds());

        if (user.getProfile() != null && user.getProfile().getId() == 1 && id == 1) {
            UserEntity admin = userService.findById(1);
            if (admin == null) {
                throw new EntityNotFoundException();
            }

            admin.setPhone(dto.getPhone());
            admin.setMobile(dto.getMobile());
            admin.setAddress(dto.getAddress());
            admin.setAvatar(ByteUtils.toByte(dto.getAvatar()));
            admin.setUsername(dto.getUsername());
            admin.setEmail(dto.getEmail());
            admin.setFirstname(dto.getFirstname());
            admin.setLastname(dto.getLastname());
            admin.setEnabled(true);
            admin.setAdmin(true);

            userService.save(user);

            return userConverter.convertTo(user);
        }

        if (user.isAdmin() && (user.getProfile() == null || user.getProfile().getId() != 1)) {
            throw new BusinessException(UserManagementErrorCode.PROTECTED_ACCOUNT);
        }

        if (!StringUtils.isEmpty(dto.getPassword())) {
            // encode passsword before saving
            String password = passwordEncoder.encode(dto.getPassword());
            user.setPassword(password);
        }

        userService.save(user);

        if (!CollectionUtils.isEmpty(excludedAuthorities)) {
            user.setExcludedAuthorities(new LinkedHashSet<>(excludedAuthorities));
        }

        userService.save(user);

        return userConverter.convertTo(user);
    }

    @Override
    public void deleteUser(Integer id) {
        isUserLogged();

        if (StringUtils.isEmpty(id)) {
            throw new MissingIdException();
        }

        UserEntity user = userService.findById(id);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (this.currentUser.getId().equals(id) || id == 1) {
            throw new UnAuthorizedException();
        }

        try {
            user.getExcludedAuthorities().clear();

            userService.delete(user);
        } catch (Exception e) {
            throw new BusinessException(UserManagementErrorCode.ALREADY_USED);
        }
    }

    @Override
    public boolean checkResetCode(Integer code, String email) {
        UserEntity user = userService.findByEmail(email);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        user = userService.findByEmailAndResetCode(email, code);

        if (user == null) {
            return false;
        }

        return true;
    }

    @Override
    public void resetPassword(Integer id, String password) {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidArgumentException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        if (StringUtils.isEmpty(password)) {
            throw new InvalidArgumentException(UserManagementErrorCode.PASSWORD_IS_MISSING);
        }

        UserEntity user = userService.findById(id);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        user.setLastPasswordResetDate(DateUtils.getDate());

        userService.save(user);
    }


    @Override
    public void resetPasswordByToken(String token, String password) {
        if (StringUtils.isEmpty(token)) {
            throw new InvalidArgumentException("token is missing");
        }

        if (StringUtils.isEmpty(password)) {
            throw new InvalidArgumentException(UserManagementErrorCode.PASSWORD_IS_MISSING);
        }

        UserEntity user = userService.findByResetToken(token);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        user.setResetToken(null);

        user.setLastPasswordResetDate(DateUtils.getDate());

        userService.save(user);
    }

    @Override
    public void resetPasswordByCode(String email, Integer code, String password) {
        if (StringUtils.isEmpty(code)) {
            throw new InvalidArgumentException(AuthenticationActivityManagementErrorCode.CODE_IS_MISSING);
        }
        if (StringUtils.isEmpty(code)) {
            throw new InvalidArgumentException(UserErrorCode.USERNAME_IS_MISSING);
        }

        if (StringUtils.isEmpty(password)) {
            throw new InvalidArgumentException(UserManagementErrorCode.PASSWORD_IS_MISSING);
        }

        UserEntity user = userService.findByEmail(email);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        user = userService.findByEmailAndResetCode(email, code);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.INVALID_RESET_CODE);
        }

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        user.setResetToken(null);
        user.setResetCode(null);

        user.setLastPasswordResetDate(DateUtils.getDate());

        userService.save(user);
    }

    @Override
    public void enableUser(Integer id) {
        isUserLogged();

        if (StringUtils.isEmpty(id)) {
            throw new InvalidArgumentException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        UserEntity user = userService.findById(id);

        if (user.isEnabled()) {
            throw new InvalidArgumentException(UserManagementErrorCode.ALREADY_ENABLED);
        }

        user.setEnabled(true);

        userService.save(user);
    }

    @Override
    public void disableUser(Integer id) {
        isUserLogged();

        if (StringUtils.isEmpty(id)) {
            throw new InvalidArgumentException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        UserEntity user = userService.findById(id);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (id == 1 || (user.getProfile() != null && user.getProfile().getId() == 1)) {
            throw new BusinessException(UserManagementErrorCode.PERMISSION_NOT_GRANTED);
        }

        if (this.currentUser.isAdmin() && (this.currentUser.getProfileId() == null || user.getProfile().getId() != 1)) {
            throw new BusinessException(UserManagementErrorCode.PERMISSION_NOT_GRANTED);
        }

        if (!user.isEnabled()) {
            throw new BusinessException(UserManagementErrorCode.ALREADY_DISABLED);
        }

        user.setEnabled(false);

        userService.save(user);
    }

    @Override
    public UserDto findUser(Integer id) {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidArgumentException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        UserEntity user = userService.findById(id);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        return userConverter.convertTo(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        UserEntity user = userService.findByUsername(username);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        return userConverter.convertTo(user);
    }

    @Override
    public List<ProfileDto> getProfiles() {
        return profileConverter.convertTo(profileService.findAll());
    }

    @Override
    public Page<ProfileDto> getProfiles(Integer page, Integer pageSize, String keyword) {
        page = (page == null || page < 1) ? 1 : page - 1;
        pageSize = (pageSize == null || pageSize < 5) ? 5 : pageSize;

        Page<ProfileEntity> profiles;

        if (!StringUtils.isEmpty(keyword)) {
            profiles = profileService.findByKeyword(keyword, new PageRequest(page, pageSize));
        } else {
            profiles = profileService.findAll(new PageRequest(page, pageSize));
        }

        return profiles.map(profileEntity -> profileConverter.convertTo(profileEntity));

    }

    @Override
    public void deleteProfile(Integer id) {
        if (!CollectionUtils.isEmpty(userService.findByProfile(profileService.findById(id)))) {
            throw new BusinessException(ProfileManagementErrorCode.ALREADY_USED);
        }

        profileService.delete(id);
    }

    @Override
    public List<AuthorityDto> getGroupedAuthorities() {
        return null;
    }

    @Override
    public List<AuthorityDto> getProfileAuthorities(Integer profileId) {
        ProfileEntity profile = profileService.findById(profileId);

        if (profile == null) {
            throw new BusinessException(ProfileManagementErrorCode.NOT_FOUND);
        }

        if (CollectionUtils.isEmpty(profile.getAuthorities())) {
            throw new NoContentException();
        }

        return authorityConverter.convertTo(profile.getAuthorities());
    }

    @Override
    public Integer saveProfile(ProfileDto profileDto) {
        profileDto.validate();

        ProfileEntity profile = profileConverter.convertFrom(profileDto);
        return profileService.save(profile).getId();
    }

    @Override
    public void editProfile(Integer id, ProfileDto profileDto) {
        if (id == null) {
            throw new BusinessException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        profileDto.validate();

        ProfileEntity profile = profileConverter.convertFrom(profileDto);

        profileService.save(profile);
    }

    @Override
    public ProfileDto findOne(Integer id) {
        ProfileDto profileDto = profileConverter.convertTo(profileService.findById(id));

        return profileDto;
    }

    @Override
    public Page<UserDto> findAll(Integer page, Integer pageSize, String keyword) {
        page = (page == null || page < 1) ? 0 : page - 1;
        pageSize = (pageSize == null || pageSize < 1) ? 5 : pageSize;

        PageRequest pageRequest = new PageRequest(page, pageSize, new Sort(
                new Sort.Order(Sort.Direction.DESC, "lastModifiedDate"),
                new Sort.Order(Sort.Direction.DESC, "createdDate")
        ));

        Page<UserEntity> users = userService.findAll(pageRequest);

        return users.map(source -> userConverter.convertTo(source));

    }

    @Override
    public void recoverPassword(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new InvalidArgumentException(UserErrorCode.EMAIL_IS_MISSING);
        }

        UserEntity user = userService.findByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (StringUtils.isEmpty(email) || !StringUtils.isValidEmail(email)) {
            throw new InvalidArgumentException(AuthenticationActivityManagementErrorCode.INVALID_EMAIL);
        }

        // Reload password post-security so we can generate token
        final JwtUser userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        user.setResetToken(token);

        userService.save(user);

        Mail mail = new Mail();

        Map<String, Object> model = new HashMap<>();

        model.put("fullname", user.getFirstname() + " " + user.getLastname());
        model.put("recoveryUrl", this.resetPasswordURL + "?token=" + token);

        mail.setModel(model);
        mail.setTo(email);

        //TODO add translation backend side
        mail.setSubject("Mail de réinitialisation du mot de passe");

        try {
            this.mailService.send(mail, "recover-password-email-template");
        } catch (MessagingException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        } catch (IOException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        }
    }

    @Override
    public void recoverPasswordByCode(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new InvalidArgumentException(UserErrorCode.EMAIL_IS_MISSING);
        }

        UserEntity user = userService.findByEmail(email);

        if (user == null) {
            throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
        }

        if (StringUtils.isEmpty(email) || !StringUtils.isValidEmail(email)) {
            throw new InvalidArgumentException(AuthenticationActivityManagementErrorCode.INVALID_EMAIL);
        }

        int code = NumberUtils.random(4);

        user.setResetCode(code);

        userService.save(user);

        Mail mail = new Mail();

        Map<String, Object> model = new HashMap<>();

        model.put("fullname", user.getFirstname() + " " + user.getLastname());
        model.put("recoveryCode", String.format("%d", code));

        mail.setModel(model);
        mail.setTo(email);

        //TODO add translation backend side
        mail.setSubject("Mail de réinitialisation du mot de passe");

        try {
            this.mailService.send(mail, "recover-password-email-code-template");
        } catch (MessagingException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        } catch (IOException e) {
            throw new BusinessException(MailBusinessErrorCode.AN_ERROR_OCCURRED_WHILE_SENDING_THE_MAIL);
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        long count = userService.count();

        //TODO remove this lines after get paid
        if (count >= 5) {
            throw new BusinessException("Oops!. Only 5 subscriptions are allowed. Please upgrade your version");
        }

        if (userDto == null) {
            throw new InvalidPayloadException();
        }

        userDto.validate();

        userDto.setId(null);

        userDto.setAuthType(AuthTypeEnum.SIMPLE);

        if (!userService.isUsernameAvailable(userDto.getId(), userDto.getUsername())) {
            throw new BusinessException(UserManagementErrorCode.DUPLICATE_USERNAME);
        }

        if (!StringUtils.isEmpty(userDto.getEmail()) && !userService.isEmailAvailable(userDto.getId(), userDto.getEmail())) {
            throw new BusinessException(UserManagementErrorCode.DUPLICATE_EMAIL);
        }

        if (StringUtils.isEmpty(userDto.getPassword())) {
            throw new BusinessException(UserManagementErrorCode.PASSWORD_IS_MISSING);
        }
        // Mark All new registration as sellers
        userDto.setProfileId(2);

        UserEntity userEntity = userConverter.convertFrom(userDto);

        // Encode the password before saving
        String password = passwordEncoder.encode(userDto.getPassword());
        userEntity.setPassword(password);

        userService.save(userEntity);

        return userConverter.convertTo(userEntity);
    }

    @Override
    public Page<UserDto> findAllByProfileId(Integer profileId, Integer page, Integer pageSize, String keyword) {
        if (StringUtils.isEmpty(profileId)) {
            throw new InvalidArgumentException(CommonManagementErrorCode.ID_IS_MISSING);
        }

        ProfileEntity profile = profileService.getById(profileId);

        if (profile == null || StringUtils.isEmpty(profile.getId())) {
            throw new EntityNotFoundException();
        }

        page = (page == null || page < 1) ? 0 : page - 1;
        pageSize = (pageSize == null || pageSize < 1) ? 5 : pageSize;

        PageRequest pageRequest = new PageRequest(page, pageSize, new Sort(
                new Sort.Order(Sort.Direction.DESC, "lastModifiedDate"),
                new Sort.Order(Sort.Direction.DESC, "createdDate")
        ));
        Page<UserEntity> users;

        if (StringUtils.isEmpty(keyword)) {
            users = userService.findAllByProfile(profile, pageRequest);
        } else {
            users = userService.findAllByProfile(profile, keyword, pageRequest);
        }

        return users.map(source -> userConverter.convertTo(source));

    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            UserEntity userEntity = this.userService.findByUsername(currentUserName);

            return userConverter.convertTo(userEntity);
        }

        return null;
    }

    @Override
    public void isUserLogged() {
        this.currentUser = this.getCurrentUser();

        if (this.currentUser == null) {
            throw new UnAuthorizedException();
        }
    }

    @Override
    public UserService getService() {
        return userService;
    }

    @Override
    public UserConverter getConverter() {
        return userConverter;
    }

    @Override
    public Page<UserListItemDto> findByFilter(FilterSortDto<UserFilterFieldsDto> filterSortDto, Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 1) ? 10 : size;

        Sort sort = filterSortConverter.convertTo(filterSortDto);
        Page<UserEntity> users = null;
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (filterSortDto == null || filterSortDto.getFilter() == null) {
            users = userService.findAll(pageRequest);
        } else {
            ProfileEntity profile = null;
            if (filterSortDto.getFilter().getProfile() != null) {
                profile = profileService.findById(filterSortDto.getFilter().getProfile());
            }
            users = userService.findAllUsers(pageRequest,
                    filterSortDto.getFilter().getFirstName(),
                    filterSortDto.getFilter().getLastName(),
                    filterSortDto.getFilter().getUsername(),
                    filterSortDto.getFilter().getEmail(),
                    profile,
                    filterSortDto.getFilter().getEnabled(),
                    filterSortDto.getFilter().getCreatedDate());
        }

        return users.map(source -> userListItemConverter.convertTo(source));

    }
}