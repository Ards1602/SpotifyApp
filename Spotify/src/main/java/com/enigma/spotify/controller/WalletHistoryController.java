package com.enigma.spotify.controller;

import com.enigma.spotify.entity.WalletHistory;
import com.enigma.spotify.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletHistoryController {
    @Autowired
    WalletHistoryService service;

    @GetMapping("/history/{id}")
    public WalletHistory getHistoryById(@PathVariable String id){
        return service.getWalletHistory(id);
    }

    @GetMapping("/history")
    public Page<WalletHistory> getAllHistory(@RequestParam Integer page,@RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllWalletHistory(pageable);
    }

    @DeleteMapping("/history/{id}")
    public void deleteHistory(@PathVariable String id){
        service.deleteWalletHistory(id);
    }
}
