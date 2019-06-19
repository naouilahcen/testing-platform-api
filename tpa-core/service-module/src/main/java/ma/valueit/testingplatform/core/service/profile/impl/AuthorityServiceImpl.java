package ma.valueit.testingplatform.core.service.profile.impl;

import ma.valueit.testingplatform.core.errorhandling.businessexception.InvalidArgumentException;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityCategoryEntity;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.repository.user.AuthorityRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.core.service.exception.AuthorityBusinessErrorCode;
import ma.valueit.testingplatform.core.service.profile.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class AuthorityServiceImpl extends BasicServiceImpl<AuthorityEntity, Integer> implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public BasicRepository<AuthorityEntity, Integer> getRepository() {
        return authorityRepository;
    }

    @Override
    public List<AuthorityEntity> findByCategory(AuthorityCategoryEntity category) {
        if(category == null) {
            throw new InvalidArgumentException(AuthorityBusinessErrorCode.CATEGORY_ID_IS_MISSING);
        }

        return authorityRepository.findByCategory(category);
    }
}
