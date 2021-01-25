package com.enigma.spotify.service;

import com.enigma.spotify.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    public Transaction doSongTransaction(Transaction transaction);
    public List<Transaction> doAlbumTransaction(Transaction transaction);
    public Transaction getTransactionById(String id);
    public Page<Transaction> getAllTransaction(Pageable pageable);
    public Page<Transaction> searchForm(Transaction transaction,Pageable pageable);
    public void deleteTransaction(String id);

}
