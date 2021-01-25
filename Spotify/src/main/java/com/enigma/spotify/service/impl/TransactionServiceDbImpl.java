package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.*;
import com.enigma.spotify.exception.AccountIsNotActiveException;
import com.enigma.spotify.exception.InsufficientBalanceException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.TransactionRepository;
import com.enigma.spotify.service.*;
import com.enigma.spotify.spesification.TransactionJpaSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceDbImpl implements TransactionService {
    @Autowired
    TransactionRepository repository;
    @Autowired
    WalletService walletService;
    @Autowired
    SongService songService;
    @Autowired
    AccountService accountService;
    @Autowired
    AlbumService albumService;


    @Override
    public Transaction doSongTransaction(Transaction transaction) {
        Wallet wallet = walletService.getWallet(transaction.getWallet().getId());
        Song song = songService.getSongById(transaction.getItem().getId());
        Account account = accountService.getAccount(wallet.getOwner().getId());

        if(account.getActive()){
            if(wallet.getBalance()>=song.getPrice()){

                transaction.setAmount(song.getPrice());
                transaction.setAlbumDiscount(0.0);
                transaction.setWallet(wallet);
                transaction.setItem(song);

                walletService.paymentWallet(wallet,song.getPrice());

                return repository.save(transaction);

            } else throw new InsufficientBalanceException(wallet.getBalance());
        }else throw new AccountIsNotActiveException(account.getId());
    }

    @Override
    public List<Transaction> doAlbumTransaction(Transaction transaction) {

        Album album = albumService.getAlbumById(transaction.getAlbum());
        Wallet wallet = walletService.getWallet(transaction.getWallet().getId());
        Account account = accountService.getAccount(wallet.getOwner().getId());

        List<Transaction> transactionList = new ArrayList<>();

        if(album.getSongs().size()!=0){
            if(account.getActive()){
                Double totalPrice =0.0;
                for (Song song:album.getSongs()) {
                    totalPrice += song.getPrice()*(1-album.getDiscount());
                }

                if(wallet.getBalance()>=totalPrice){

                    for (Song song:album.getSongs()) {
                        //Transaction Record
                        Transaction songTransaction = new Transaction();
                        songTransaction.setItem(song);
                        songTransaction.setWallet(wallet);
                        songTransaction.setAlbumDiscount(album.getDiscount());
                        songTransaction.setAmount(song.getPrice()*(1-album.getDiscount()));
                        songTransaction.setTrxDate(transaction.getTrxDate());

                        repository.save(songTransaction);
                        transactionList.add(songTransaction);

                        //Wallet Record
                        walletService.paymentWallet(wallet,songTransaction.getAmount());
                    }

                } else throw new InsufficientBalanceException(wallet.getBalance());
            } else throw new AccountIsNotActiveException(account.getId());
        }else throw new ResourceNotFoundException(album.getId());

        return transactionList;
    }

    @Override
    public Transaction getTransactionById(String id) {
        Transaction transaction = repository.findById(id).get();
        return transaction;
    }

    @Override
    public Page<Transaction> getAllTransaction(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Transaction> searchForm(Transaction transaction, Pageable pageable) {
        return repository.findAll(TransactionJpaSpesification.findByCriterias(transaction),pageable);
    }

    @Override
    public void deleteTransaction(String id) {
        repository.deleteById(id);
    }
}
