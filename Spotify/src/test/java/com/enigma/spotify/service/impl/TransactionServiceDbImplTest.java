package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.entity.Transaction;
import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.repository.TransactionRepository;
import com.enigma.spotify.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceDbImplTest {

    @Autowired
    TransactionService transactionServiceDB;

    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach
    public void cleanUp(){
        transactionRepository.deleteAll();
    }

    @Test//song
    void doSongTransaction() {
        Transaction transaction=new Transaction(4000.0,0.0,new Date()
                ,new Song("sad",2000,500,4000.0),new Wallet(900000.0));
        transactionServiceDB.doSongTransaction(transaction);
        assertEquals(1,transactionRepository.findAll().size());
    }

    @Test//album
    void doAlbumTransaction() {
    }

    @Test
    void getTransactionById() {
        Transaction transaction=new Transaction(4000.0,0.0,new Date()
                ,new Song("sad",2000,500,4000.0),new Wallet(900000.0));
        Transaction transaction2=new Transaction(5000.0,0.0,new Date()
                ,new Song("sadu",2000,500,4000.0),new Wallet(900000.0));
        transactionRepository.save(transaction);
        transactionRepository.save(transaction2);
        assertEquals(transaction2,transactionServiceDB.getTransactionById(transaction2.getId()));

    }

    @Test
    void getAllTransaction() {
        Transaction transaction=new Transaction(4000.0,0.0,new Date()
                ,new Song("sad",2000,500,4000.0),new Wallet(900000.0));
        Transaction transaction2=new Transaction(5000.0,0.0,new Date()
                ,new Song("sadu",2000,500,4000.0),new Wallet(900000.0));
        transactionRepository.save(transaction);
        transactionRepository.save(transaction2);
        assertEquals(2,transactionServiceDB.getAllTransaction(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void searchForm() {
    }

    @Test
    void deleteTransaction() {
    }
}