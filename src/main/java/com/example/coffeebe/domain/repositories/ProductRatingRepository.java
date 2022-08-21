package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
    List<ProductRating> findAllByProduct(Product product);
}
