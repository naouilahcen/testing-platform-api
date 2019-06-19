package ma.valueit.testingplatform.manager.test;

import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.CategoryConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.CategoryDto;
import ma.valueit.testingplatform.model.category.CategoryEntity;
import ma.valueit.testingplatform.service.test.CategoryService;

public interface CategoryManager extends CrudManager<CategoryDto, Integer, CategoryEntity, CategoryService, CategoryConverter> {
}

