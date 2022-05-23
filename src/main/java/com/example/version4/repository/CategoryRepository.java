package com.example.version4.repository;

import com.example.version4.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.name like concat('%', ?1, '%')")
    List<Category> findByNameContaining(String Name);

    @Query("select c from Category c where c.name like concat('%', ?1, '%')")
    Page<Category> findByNameContaining(String Name, Pageable pageable);
}
