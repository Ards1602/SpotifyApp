package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.entity.Wallet;
import com.enigma.spotify.entity.WalletHistory;
import com.enigma.spotify.enums.HistoryEnum;
import com.enigma.spotify.exception.AccountIsNotActiveException;
import com.enigma.spotify.exception.InsufficientBalanceException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.WalletRepository;
import com.enigma.spotify.service.AccountService;
import com.enigma.spotify.service.WalletHistoryService;
import com.enigma.spotify.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WalletServiceDbImpl implements WalletService {
    @Autowired
    WalletRepository repository;

    @Autowired
    WalletHistoryService historyService;
    @Autowired
    AccountService accountService;

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return repository.save(wallet);
    }

    @Override
    public Wallet getWallet(String id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Wallet> getAllWallet(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteWallet(String id) {
        repository.deleteById(id);
    }

    @Override
    public Wallet topupWallet(Wallet wallet) {

        Wallet updateWallet = repository.findById(wallet.getId()).get();
        Account account = accountService.getAccount(updateWallet.getOwner().getId());

        if(account.getActive()){

            updateWallet.setBalance(updateWallet.getBalance()+wallet.getTopup());

            WalletHistory history = new WalletHistory(HistoryEnum.TOPUP,wallet.getTopup());
            history.setWallet(updateWallet);
            history.setTrxDate(new Date());
            historyService.saveWalletHistory(history);

            return repository.save(updateWallet);

        } else throw new AccountIsNotActiveException(account.getId());
    }

    @Override
    public Wallet withdrawalWallet(Wallet wallet) {
        Wallet updateWallet = repository.findById(wallet.getId()).get();
        Account account = accountService.getAccount(updateWallet.getOwner().getId());

        if (account.getActive()) {
            if (updateWallet.getBalance() >= wallet.getWithdrawal()) {
                updateWallet.setBalance(updateWallet.getBalance() - wallet.getWithdrawal());

                WalletHistory history = new WalletHistory(HistoryEnum.WITHDRAWAL, wallet.getWithdrawal());
                history.setTrxDate(new Date());
                history.setWallet(updateWallet);
                historyService.saveWalletHistory(history);

                return repository.save(updateWallet);
            } else throw new InsufficientBalanceException(updateWallet.getBalance());
        } else throw new AccountIsNotActiveException(account.getId());
    }

    @Override
    public Wallet paymentWallet(Wallet wallet,Double amount) {
        Wallet updateWallet = repository.findById(wallet.getId()).get();
        Account account = accountService.getAccount(updateWallet.getOwner().getId());

        if(account.getActive()){
            updateWallet.setBalance(updateWallet.getBalance()-amount);

            WalletHistory history = new WalletHistory(HistoryEnum.PAYMENT,amount);
            history.setTrxDate(new Date());
            history.setWallet(updateWallet);
            historyService.saveWalletHistory(history);

            return repository.save(updateWallet);
        } else throw new AccountIsNotActiveException(account.getId());
    }
}
