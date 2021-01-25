package com.enigma.spotify.service;

import com.enigma.spotify.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    public Account saveAccount(Account account);
    public Account getAccount(String id);
    public Page<Account> getAllAccount(Pageable pageable);
    public void deleteAccount(String id);
}
