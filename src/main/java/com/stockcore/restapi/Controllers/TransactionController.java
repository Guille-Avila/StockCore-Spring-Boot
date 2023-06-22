package com.stockcore.restapi.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.stockcore.restapi.Models.Transaction;
import com.stockcore.restapi.Repositories.TransactionRepository;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository data;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return data.findAll();
    }

    @GetMapping("/transactions-business/{id}")
    public List<Transaction> geTransactionsByBusiness(@PathVariable Long id) {
        return data.findByProductBusinessId(id);
    }

    @GetMapping("/transactions-business/{businessId}/type/{typeId}")
    public List<Transaction> getTransactionsByBusinessAndType(@PathVariable long businessId, @PathVariable long typeId) {
        return data.findByProductBusinessIdAndTypeId(businessId, typeId);
    }
    
}
