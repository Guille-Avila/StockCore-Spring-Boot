package com.stockcore.restapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockcore.restapi.Models.Business;

public interface BusinessRepository extends JpaRepository<Business, Long>{
    
}
