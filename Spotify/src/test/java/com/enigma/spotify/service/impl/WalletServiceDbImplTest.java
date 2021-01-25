package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.repository.WalletRepository;
import com.enigma.spotify.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletServiceDbImplTest {

    @Autowired
    WalletService walletServiceDB;

    @Autowired
    WalletRepository walletRepository;

    @BeforeEach
    public void cleanUp(){
        walletRepository.deleteAll();
    }

    @Test
    void saveWallet_shouldAdd_1Data_inDB_whenWalletSaved() {
        Wallet wallet=new Wallet(20000.0);
        walletServiceDB.saveWallet(wallet);
        assertEquals(1,walletRepository.findAll().size());
    }

    @Test
    void getWallet_shouldGetWallet_whenGivenCorrectId() {
        Wallet wallet=new Wallet(20000.0);
        Wallet wallet2=new Wallet(40000.0);
        walletRepository.save(wallet);
        walletRepository.save(wallet2);
        assertEquals(wallet2,walletServiceDB.getWallet(wallet2.getId()));
    }

    @Test
    void getAllWallet_shouldBe_2InDB_whenDataInDBIs_2() {
        Wallet wallet=new Wallet(20000.0);
        Wallet wallet2=new Wallet(40000.0);
        walletRepository.save(wallet);
        walletRepository.save(wallet2);
        assertEquals(2,walletServiceDB.getAllWallet(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void deleteWallet() {
        Wallet wallet=new Wallet(20000.0);
        Wallet wallet2=new Wallet(40000.0);
        walletRepository.save(wallet);
        walletRepository.save(wallet2);
        walletServiceDB.deleteWallet(wallet2.getId());
        assertEquals(1,walletRepository.findAll().size());
    }

    @Test
    void topupWallet() {
    }

    @Test
    void withdrawalWallet() {
    }
}