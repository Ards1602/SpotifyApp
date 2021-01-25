package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.AccountRepository;
import com.enigma.spotify.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceDbImpl implements AccountService {
    @Autowired
    AccountRepository repository;

    @Override
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account getAccount(String id) {
        Account account = null;
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Account> getAllAccount(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteAccount(String id) {
        repository.deleteById(id);
    }

}
