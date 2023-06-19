package com.stockcore.restapi.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.stockcore.restapi.Model.Product;
import com.stockcore.restapi.Repository.ProductRepository;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository data;

    @GetMapping(value = "/")
    public String holaMundo() {
        return "Hola Mundo!!!!";
    }

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return data.findAll();
    }

    @PostMapping("/create-product")
    public String createProduct(@RequestBody Product newProduct) {
        data.save(newProduct);
        
        return "Product created!";
    }
    
    @PutMapping("/edit-product/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id, @RequestBody Product productToEdit) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        Product updatedProduct = data.findById(id).orElse(null);
        updatedProduct.setBusiness(productToEdit.getBusiness());;
        updatedProduct.setCategory(productToEdit.getCategory());
        updatedProduct.setName(productToEdit.getName());
        updatedProduct.setDescription(productToEdit.getDescription());
        updatedProduct.setAmount(productToEdit.getAmount());
        updatedProduct.setPrice(productToEdit.getPrice());
        
        data.save(updatedProduct);
        
        return ResponseEntity.ok("Product updated!");
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        data.deleteById(id);
        
        return ResponseEntity.ok("Product deleted!");
    }
    
}
