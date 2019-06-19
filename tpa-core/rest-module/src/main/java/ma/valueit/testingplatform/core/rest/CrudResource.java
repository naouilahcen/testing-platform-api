package ma.valueit.testingplatform.core.rest;

import ma.valueit.testingplatform.core.errorhandling.businessexception.ResponseBody;
import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public abstract class CrudResource<DTO extends DtoWithID<ID>, ID extends Serializable, CRUD_MANAGER extends CrudManager> {
    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @GetMapping
    public ResponseEntity<ResponseBody<List<DTO>>> getAll() {
        List<DTO> result = this.getCrudManager().findAll();

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.OK);
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<DTO>>> getPagedList(@RequestParam(value = "page", required = true) Integer page,
                                                                @RequestParam(value = "size", required = true) Integer size) {
        Page<DTO> result = this.getCrudManager().findAll(page, size);

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.OK);
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<DTO>> get(@PathVariable("id") Integer id) {
        DTO result = (DTO) this.getCrudManager().find(id);

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.OK);
    }


    /**
     * Creates a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param dto must not be {@literal null}.
     * @return the saved entity
     */
    @PostMapping
    public ResponseEntity<ResponseBody<DTO>> create(@RequestBody DTO dto) {
        DTO result = (DTO) this.getCrudManager().create(dto);

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.CREATED);
    }

    /**
     * Updates a given entity
     *
     * @param id  must not be {@literal null}. it's used to retrieve the concerned entity
     * @param dto must not be {@literal null}.
     * @return the edited entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody<DTO>> edit(@PathVariable("id") Integer id, @RequestBody DTO dto) {
        DTO result = (DTO) this.getCrudManager().edit(id, dto);

        return new ResponseEntity<>(new ResponseBody<>(result), HttpStatus.OK);
    }


    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        this.getCrudManager().delete(id);

        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    /**
     * Returns the Entity CRUD Manager
     *
     * @return the CRUD_MANAGER
     */
    protected abstract CRUD_MANAGER getCrudManager();
}