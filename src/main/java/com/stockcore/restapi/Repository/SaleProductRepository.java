package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.SaleProduct;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long>{
    
}
