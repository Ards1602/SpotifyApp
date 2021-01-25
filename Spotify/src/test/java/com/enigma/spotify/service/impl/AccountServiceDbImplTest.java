package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Account;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.AccountRepository;
import com.enigma.spotify.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceDbImplTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountServiceDB;

    @BeforeEach
    public void cleanUp(){
        accountRepository.deleteAll();
    }

    @Test
    void saveAccount__shouldAdd_1Data_whenAccountSaved() {
        Account account=new Account(Boolean.TRUE);
        accountServiceDB.saveAccount(account);
        assertEquals(1,accountRepository.findAll().size());
    }

    @Test
    void getAccountById_shouldGetAccount_whenGivenCorrectId() {
        Account account=new Account(Boolean.TRUE);
        Account account2=new Account(Boolean.FALSE);
        accountRepository.save(account);
        accountRepository.save(account2);
        assertEquals(account2, accountServiceDB.getAccount(account2.getId()));
    }

    @Test
    void getAccountById_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            accountServiceDB.getAccount("asdasd");
        });
    }

    @Test
    void getAllAccount_shouldBe_2InDB_whenDataInDBIs_2() {
        Account account=new Account(Boolean.TRUE);
        Account account2=new Account(Boolean.FALSE);
        accountRepository.save(account);
        accountRepository.save(account2);
        assertEquals(2, accountServiceDB.getAllAccount(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void deleteAccount_shouldDelete_1Data_inDB_whenAccountDeleted() {
        Account account=new Account(Boolean.TRUE);
        Account account2=new Account(Boolean.FALSE);
        accountRepository.save(account);
        accountRepository.save(account2);
        accountServiceDB.deleteAccount(account2.getId());
        assertEquals(1,accountRepository.findAll().size());

    }
}