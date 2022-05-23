package com.example.version4.service;

import com.example.version4.domain.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CategoryService {
    List<Category> findAll();

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Long> longs);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    <S extends Category> S saveAndFlush(S entity);

    <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();
    Optional<Category> findById(Long aLong);

    Category getById(Long aLong);

    <S extends Category> List<S> findAll(Example<S> example);

    <S extends Category> List<S> findAll(Example<S> example, Sort sort);

    List<Category> findByNameContaining(String name);

    Page<Category> findByNameContaining(String Name, Pageable pageable);

    Page<Category> findAll(Pageable pageable);

    <S extends Category> S save(S entity);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Category entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Category> entities);

    void deleteAll();

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Category> boolean exists(Example<S> example);

    <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
