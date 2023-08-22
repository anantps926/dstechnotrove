package com.example.dstechnotrove.repository;
import com.example.dstechnotrove.domain.Product;
import com.example.dstechnotrove.domain.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    List<ProductVariant> findByProduct(Product product);
}