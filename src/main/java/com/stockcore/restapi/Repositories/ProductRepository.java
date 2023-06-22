package com.stockcore.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockcore.restapi.Models.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByBusinessId(Long businessId);
}
