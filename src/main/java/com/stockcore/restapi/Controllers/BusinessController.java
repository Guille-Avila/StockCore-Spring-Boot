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

import com.stockcore.restapi.Models.Business;
import com.stockcore.restapi.Repositories.BusinessRepository;


@RestController
public class BusinessController {
    
    @Autowired
    private BusinessRepository data;

    @GetMapping("/business")
    public List<Business> getBusiness() {
        return data.findAll();
    }

    
    @GetMapping("/business/{id}")
    public Optional<Business> getBusinessById(@PathVariable Long id) {
        return data.findById(id);
    }

    @PostMapping("/create-business")
    public String createBusiness(@RequestBody Business newBusiness) {
        data.save(newBusiness);
        return "Business created!";
    }

    @PutMapping("/edit-business/{id}")
    public String editBusiness(@PathVariable Long id, @RequestBody Business businessToEdit) {

        if (!data.existsById(id)) {
            return "Bussines not found!";
        }

        Business updatedBusiness = data.findById(id).orElse(null);
        updatedBusiness.setEmail(businessToEdit.getEmail());
        updatedBusiness.setName(businessToEdit.getName());
        updatedBusiness.setPhone(businessToEdit.getPhone());

        data.save(updatedBusiness);

        return "Business Edited!";
    }

    @DeleteMapping("/delete-business/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        data.deleteById(id);
        
        return ResponseEntity.ok("Business deleted!");
    }

}
