package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
