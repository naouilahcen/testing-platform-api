package ma.valueit.testingplatform.manager.test.impl;

import ma.valueit.testingplatform.core.manger.impl.CrudManagerImpl;
import ma.valueit.testingplatform.manager.test.CategoryManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.CategoryConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.CategoryDto;
import ma.valueit.testingplatform.model.category.CategoryEntity;
import ma.valueit.testingplatform.service.test.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagerImpl extends CrudManagerImpl<CategoryDto, Integer, CategoryEntity, CategoryService, CategoryConverter> implements CategoryManager {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryConverter categoryConverter;


    @Override
    public CategoryService getService() {
        return categoryService;
    }

    @Override
    public CategoryConverter getConverter() {
        return categoryConverter;
    }
}
