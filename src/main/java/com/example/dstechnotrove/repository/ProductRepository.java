package com.example.dstechnotrove.repository;
import com.example.dstechnotrove.domain.Category;
import com.example.dstechnotrove.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
