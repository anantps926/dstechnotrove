package com.example.dstechnotrove.repository;

import com.example.dstechnotrove.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
