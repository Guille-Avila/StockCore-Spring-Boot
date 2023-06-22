package com.stockcore.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockcore.restapi.Models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
