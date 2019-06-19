package ma.valueit.testingplatform.core.service;

import ma.valueit.testingplatform.core.repository.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BasicService<T, ID extends Serializable> {

    BasicRepository<T, ID> getRepository();

    T save(T entity);

    List<T> save(Iterable<T> entities);

    T saveAndFlush(T entity);

    List<T> saveAndFlush(Iterable<T> entities);

    T findById(ID id);

    T initializeAndUnProxy(T entity);

    T getById(ID id);

    void deleteById(ID id);

    List<T> findAll();

    List<T> findAll(Iterable<ID> ids);

    List<T> getAll(Iterable<ID> ids);

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageRequest);

    long count();

    List<T> queryEntityListBySql(String sql, Map<String, Object> paramMap);

    void delete(ID id);

    void delete(T entity);

    void delete(List<T> entitys);

    void delete(Iterable<ID> ids);

    int executeSql(String sql, Map<String, Object> paramMap);

}
