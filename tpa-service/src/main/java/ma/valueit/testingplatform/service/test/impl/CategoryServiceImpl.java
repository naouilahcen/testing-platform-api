package ma.valueit.testingplatform.service.test.impl;

import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.model.category.CategoryEntity;
import ma.valueit.testingplatform.repository.test.CategoryRepository;
import ma.valueit.testingplatform.service.test.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BasicServiceImpl<CategoryEntity, Integer> implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public BasicRepository<CategoryEntity, Integer> getRepository() {
        return categoryRepository;
    }
}
