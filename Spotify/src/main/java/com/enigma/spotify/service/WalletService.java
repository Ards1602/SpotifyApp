package com.enigma.spotify.service;

import com.enigma.spotify.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    public Wallet saveWallet(Wallet wallet);
    public Wallet getWallet(String id);
    public Page<Wallet> getAllWallet(Pageable pageable);
    public void deleteWallet(String id);
    public Wallet topupWallet(Wallet wallet);
    public Wallet withdrawalWallet(Wallet wallet);
    public Wallet paymentWallet(Wallet wallet,Double amount);
}
