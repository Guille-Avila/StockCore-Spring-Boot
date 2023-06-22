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

import com.stockcore.restapi.Models.Product;
import com.stockcore.restapi.Repositories.ProductRepository;
import com.stockcore.restapi.Services.TransactionService;


@RestController
public class ProductController {

    private final TransactionService transactionService;

    public ProductController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

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

    @GetMapping("products/{businessId}")
    public List<Product> getProductsByBusinessId(@PathVariable Long businessId) {
        return data.findByBusinessId(businessId);
    }

    @GetMapping(value = "/product/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return data.findById(id);
    }

    @PostMapping("/create-product")
    public String createProduct(@RequestBody Product newProduct) {

        data.save(newProduct);

        int amountNewProduct = newProduct.getAmount();

        if (amountNewProduct > 0) {
            transactionService.transactionNewProduct(amountNewProduct, newProduct);
        }
        
        return "Product created!";
    }
    
    @PutMapping("/edit-product/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id, @RequestBody Product productToEdit) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        Product updatedProduct = data.findById(id).orElse(null);

        if (productToEdit.getAmount() != updatedProduct.getAmount()) {
            transactionService.createTransaction(updatedProduct, productToEdit.getAmount());
        }

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
