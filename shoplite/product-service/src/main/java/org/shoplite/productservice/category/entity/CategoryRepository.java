package org.shoplite.productservice.category.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByType(CategoryType type);

    @Query("SELECT c from Category c where c.delFlag = false")
    List<Category> findAllActive();

}
