package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
