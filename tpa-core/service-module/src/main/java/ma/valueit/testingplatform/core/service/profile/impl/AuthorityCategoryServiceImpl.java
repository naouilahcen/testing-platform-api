package ma.valueit.testingplatform.core.service.profile.impl;

import ma.valueit.testingplatform.core.model.entity.user.AuthorityCategoryEntity;
import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.repository.user.AuthorityCategoryRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.core.service.profile.AuthorityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yelansari on 2/23/18.
 */
@Service
public class AuthorityCategoryServiceImpl extends BasicServiceImpl<AuthorityCategoryEntity, Integer> implements AuthorityCategoryService {
    @Autowired
    private AuthorityCategoryRepository authorityRepository;

    @Override
    public BasicRepository<AuthorityCategoryEntity, Integer> getRepository() {
        return authorityRepository;
    }

}
