package ma.valueit.testingplatform.rest;


import ma.valueit.testingplatform.core.errorhandling.businessexception.ResponseBody;
import ma.valueit.testingplatform.core.manger.mapper.dto.FilterSortDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserFilterFieldsDto;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserListItemDto;
import ma.valueit.testingplatform.core.manger.user.UserManager;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.rest.CrudResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by naoui on 14/05/19.
 */
@RestController
@RequestMapping("/users")
public class UserResource extends CrudResource<UserDto, Integer, UserManager> {

    @Autowired
    protected UserManager userManager;

    @PostMapping("/filter-paged-list")
    public ResponseEntity<ResponseBody<Page<UserListItemDto>>> getFilteredAndPagedList(
            @RequestBody FilterSortDto<UserFilterFieldsDto> filterSortDto,
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "size", required = true) Integer size) {
        Page<UserListItemDto> result = this.getCrudManager().findByFilter(filterSortDto, page, size);

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.OK);
    }

    @Override
    protected UserManager getCrudManager() {
        return null;
    }
}

