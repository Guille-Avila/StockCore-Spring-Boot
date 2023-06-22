package com.stockcore.restapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockcore.restapi.Models.Product;
import com.stockcore.restapi.Repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository data;

    public void updateProductAmount(Long id, int amount) {
        
        if (!data.existsById(id)) {
            return;
        }

        Product productToUpdate = data.findById(id).orElse(null);
        int lastAmount = productToUpdate.getAmount();
        int newAmount = lastAmount + amount;
        
        productToUpdate.setAmount(newAmount);

        data.save(productToUpdate);

    }
    
}
