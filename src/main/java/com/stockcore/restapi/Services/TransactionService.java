package com.stockcore.restapi.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockcore.restapi.Models.Transaction;
import com.stockcore.restapi.Models.TransactionType;
import com.stockcore.restapi.Models.Product;
import com.stockcore.restapi.Repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository data;

    private final ProductService productService;

    public TransactionService(ProductService productService) {
        this.productService = productService;
    }

    public void transactionNewProduct(int amountProduct, Product product){
        
        Transaction newTransaction = new Transaction();
        TransactionType type = new TransactionType();

        type.setId(1);

        newTransaction.setAmount(amountProduct);
        newTransaction.setProduct(product);
        newTransaction.setType(type);

        data.save(newTransaction);

    }

    public void createTransaction(Product product, int newAmount){

        Transaction newTransaction = new Transaction();
        TransactionType type = new TransactionType();
        int difAmount = 0;

        if (newAmount > product.getAmount()){
            type.setId(1);
            difAmount = newAmount - product.getAmount();
        } else {
            type.setId(2);
            difAmount = product.getAmount() - newAmount;
        }

        newTransaction.setAmount(difAmount);
        newTransaction.setProduct(product);
        newTransaction.setType(type);

        data.save(newTransaction);

    }

    public Transaction updateTransactionAndProduct(Transaction updatedEntry, Transaction entryToEdit, Long newProductId, Long lastProductId) {

        updatedEntry.setProduct(entryToEdit.getProduct());
        updatedEntry.setAmount(entryToEdit.getAmount());

        productService.updateProductAmount(newProductId, entryToEdit.getAmount());
        productService.updateProductAmount(lastProductId, updatedEntry.getAmount() * -1);

        return updatedEntry;

    }

    public Transaction updateTransactionWithoutProductChange(Transaction updatedEntry, Transaction entryToEdit) {
        int newAmount = entryToEdit.getAmount();
        int lastAmount = updatedEntry.getAmount();

        if (newAmount != lastAmount) {
            updatedEntry.setAmount(newAmount);
            productService.updateProductAmount(updatedEntry.getProduct().getId(), lastAmount - newAmount);
        }

        return updatedEntry;
    }
        
}
