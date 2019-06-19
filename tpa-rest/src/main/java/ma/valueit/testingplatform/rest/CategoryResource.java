package ma.valueit.testingplatform.rest;

import ma.valueit.testingplatform.core.rest.CrudResource;
import ma.valueit.testingplatform.manager.test.CategoryManager;
import ma.valueit.testingplatform.manager.test.mapper.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryResource extends CrudResource<CategoryDto, Integer, CategoryManager> {

    @Autowired
    CategoryManager categoryManager;
    @Override
    protected CategoryManager getCrudManager() {
        return categoryManager;
    }
}
