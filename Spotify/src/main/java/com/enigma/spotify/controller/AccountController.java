package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/account")
    public Account saveAccount(@RequestBody Account account){
        return service.saveAccount(account);
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable String id){
        return service.getAccount(id);
    }

    @GetMapping("/account")
    public Page<Account> getAllAccount(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllAccount(pageable);
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable String id){
        service.deleteAccount(id);
    }
}
