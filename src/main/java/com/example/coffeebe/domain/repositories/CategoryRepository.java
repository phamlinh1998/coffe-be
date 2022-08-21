package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("select c from Category c order by c.parentId asc")
//    List<Category> getAllCategory();


    boolean existsByName(String name);

}
