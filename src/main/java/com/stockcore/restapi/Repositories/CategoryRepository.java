package com.stockcore.restapi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockcore.restapi.Models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByBusinessId(long businessId);
}
