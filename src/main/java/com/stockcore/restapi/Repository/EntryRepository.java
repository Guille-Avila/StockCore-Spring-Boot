package com.stockcore.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long>{
    
}
