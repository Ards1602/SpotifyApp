package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionService service;

    @PostMapping("/transaction/song")
    public Transaction doSongTransaction(@RequestBody Transaction transaction){
        return service.doSongTransaction(transaction);
    }
    @PostMapping("/transaction/album")
    public List<Transaction> doAlbumTransaction(@RequestBody Transaction transaction){
        return service.doAlbumTransaction(transaction);
    }
    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable String id){
        return service.getTransactionById(id);
    }

    @GetMapping("/transaction")
    public Page<Transaction> getAllTransaction(@RequestParam Integer page,@RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllTransaction(pageable);
    }

    @GetMapping("transaction/search")
    public Page<Transaction> searchTransaction(@RequestBody Transaction transaction,@RequestParam Integer page,@RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.searchForm(transaction,pageable);
    }

    @DeleteMapping("/transaction/{id}")
    public void deleteTransaction(@PathVariable String id){
        service.deleteTransaction(id);
    }

}
