package com.stockcore.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockcore.restapi.Models.SaleProduct;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long>{
    
}
