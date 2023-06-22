package com.stockcore.restapi.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockcore.restapi.Models.Sale;
import com.stockcore.restapi.Repositories.SaleRepository;

@RestController
public class SaleController {

    @Autowired
    private SaleRepository data;
    
    @GetMapping("/sales")
    public List<Sale> getSales() {
        return data.findAll();
    }

    @PostMapping("/create-sale")
    public String createSale(@RequestBody Sale newSale) {
        data.save(newSale);
        return "Sale created!";
    }

    @PutMapping("/edit-sale/{id}")
    public String editSale(@PathVariable Long id, @RequestBody Sale saleToEdit) {

        Optional<Sale> optionalSale = data.findById(id);

        if (optionalSale.isEmpty()) {
            return "Sale not found!";
        }

        Sale existingSale = optionalSale.get();
        existingSale.setAmount(saleToEdit.getAmount());
        existingSale.setTotal(saleToEdit.getTotal());

        data.save(existingSale);

        return "Entry Edited!";
    }

    @DeleteMapping("/delete-sale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        data.deleteById(id);
        
        return ResponseEntity.ok("Sale deleted!");
    }
}
