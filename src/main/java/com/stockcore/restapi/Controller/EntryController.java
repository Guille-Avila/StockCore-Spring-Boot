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
import com.stockcore.restapi.Model.Entry;
import com.stockcore.restapi.Repository.EntryRepository;


@RestController
public class EntryController {

    @Autowired
    private EntryRepository data;

    @GetMapping("/entries")
    public List<Entry> getEntries() {
        return data.findAll();
    }

    @PostMapping("/create-entry")
    public String createEntry(@RequestBody Entry newEntry) {
        data.save(newEntry);
        return "Entry created!";
    }

    @PutMapping("/edit-entry/{id}")
    public String editEntry(@PathVariable Long id, @RequestBody Entry entryToEdit) {

        if (!data.existsById(id)) {
            return "Entry not found!";
        }

        Entry updatedEntry = data.findById(id).orElse(null);
        updatedEntry.setProduct(entryToEdit.getProduct());
        updatedEntry.setDate(entryToEdit.getDate());
        updatedEntry.setAmount(entryToEdit.getAmount());
        updatedEntry.setDetails(entryToEdit.getDetails());

        data.save(updatedEntry);

        return "Entry Edited!";
    }

    @DeleteMapping("/delete-entry/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        
        if (!data.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        data.deleteById(id);
        
        return ResponseEntity.ok("Entry deleted!");
    }
    
}
