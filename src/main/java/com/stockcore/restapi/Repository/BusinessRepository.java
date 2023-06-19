package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long>{
    
}
