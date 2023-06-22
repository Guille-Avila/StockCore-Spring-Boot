package com.stockcore.restapi.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.stockcore.restapi.Models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByProductBusinessId(long businessId);
    List<Transaction> findByProductBusinessIdAndTypeId(long businessId, long typeId);

}
