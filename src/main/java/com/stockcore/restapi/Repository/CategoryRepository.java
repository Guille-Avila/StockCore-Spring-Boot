package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
