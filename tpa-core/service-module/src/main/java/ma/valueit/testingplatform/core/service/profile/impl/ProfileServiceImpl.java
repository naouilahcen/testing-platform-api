package ma.valueit.testingplatform.core.service.profile.impl;

import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.repository.user.ProfileRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.core.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class ProfileServiceImpl extends BasicServiceImpl<ProfileEntity, Integer> implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public BasicRepository<ProfileEntity, Integer> getRepository() {
        return profileRepository;
    }

    @Override
    public Page<ProfileEntity> findByKeyword(String keyword, Pageable pageable) {
        return profileRepository.findByKeyword(keyword, pageable);
    }
}
