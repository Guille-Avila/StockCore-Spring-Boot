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
import com.stockcore.restapi.Model.Category;
import com.stockcore.restapi.Repository.CategoryRepository;

@RestController
public class CategoryController {
    
    @Autowired
    private CategoryRepository data;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return data.findAll();
    }

    @PostMapping("/create-category")
    public String createBusiness(@RequestBody Category newCategory) {
        data.save(newCategory);
        return "Category created!";
    }

    @PutMapping("/edit-category/{id}")
    public String editCategory(@PathVariable Long id, @RequestBody Category categoryToEdit) {

        if (!data.existsById(id)) {
            return "Category not found!";
        }

        Category updatedCategory = data.findById(id).orElse(null);
        updatedCategory.setName(categoryToEdit.getName());

        data.save(updatedCategory);

        return "Category Edited!";
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        data.deleteById(id);
        
        return ResponseEntity.ok("Category deleted!");
    }

}
