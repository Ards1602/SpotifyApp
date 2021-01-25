package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    @Autowired
    WalletService service;

    @PostMapping("/wallet")
    public Wallet saveWallet(@RequestBody Wallet wallet){
        return service.saveWallet(wallet);
    }

    @GetMapping("/wallet/{id}")
    public Wallet getWalletById(@PathVariable String id){
        return service.getWallet(id);
    }

    @GetMapping("/wallet")
    public Page<Wallet> getAllWallet(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllWallet(pageable);
    }
    @DeleteMapping("/wallet/{id}")
    public void deleteWallet (@PathVariable String id){
        service.deleteWallet(id);
    }

    @PostMapping("/wallet/topup")
    public Wallet topupWallet(@RequestBody Wallet wallet){
        return service.topupWallet(wallet);
    }

    @PostMapping("/wallet/withdrawal")
    public Wallet withdrawalWallet(@RequestBody Wallet wallet){
        return service.withdrawalWallet(wallet);
    }
}
