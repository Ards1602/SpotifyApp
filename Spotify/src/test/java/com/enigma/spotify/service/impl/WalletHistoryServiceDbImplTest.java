package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.WalletHistory;
import com.enigma.spotify.enums.HistoryEnum;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.WalletHistoryRepository;
import com.enigma.spotify.service.WalletHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletHistoryServiceDbImplTest {

    @Autowired
    WalletHistoryService walletHistoryServiceDB;

    @Autowired
    WalletHistoryRepository walletHistoryRepository;

    @BeforeEach
    public void cleanUp(){
        walletHistoryRepository.deleteAll();
    }

    @Test
    void saveWalletHistory_shouldAdd_1Data_inDB_whenWalletHistorySaved() {
        WalletHistory walletHistory=new WalletHistory(HistoryEnum.TOPUP,40.0);
        walletHistoryServiceDB.saveWalletHistory(walletHistory);
        assertEquals(1,walletHistoryRepository.findAll().size());
    }

    @Test
    void getWalletHistory_History_shouldGetWalletHistory_whenGivenCorrectId() {
        WalletHistory walletHistory=new WalletHistory(HistoryEnum.TOPUP,40.0);
        WalletHistory walletHistory2=new WalletHistory(HistoryEnum.WITHDRAWAL,40.0);
        walletHistoryRepository.save(walletHistory);
        walletHistoryRepository.save(walletHistory2);
        assertEquals(walletHistory2,walletHistoryServiceDB.getWalletHistory(walletHistory2.getId()));
    }

    @Test
    void getAllWallet_shouldBe_2InDB_whenDataInDBIs_2() {
        WalletHistory walletHistory=new WalletHistory(HistoryEnum.TOPUP,40.0);
        WalletHistory walletHistory2=new WalletHistory(HistoryEnum.WITHDRAWAL,40.0);
        walletHistoryRepository.save(walletHistory);
        walletHistoryRepository.save(walletHistory2);
        assertEquals(2,walletHistoryServiceDB.getAllWalletHistory(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getWalletHistory_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            walletHistoryServiceDB.getWalletHistory("asdasd");
        });
    }

    @Test
    void deleteWalletHistory_shouldDelete_1Data_inDB_whenWalletHistoryDeleted() {
        WalletHistory walletHistory=new WalletHistory(HistoryEnum.TOPUP,40.0);
        WalletHistory walletHistory2=new WalletHistory(HistoryEnum.WITHDRAWAL,40.0);
        walletHistoryRepository.save(walletHistory);
        walletHistoryRepository.save(walletHistory2);
        walletHistoryServiceDB.deleteWalletHistory(walletHistory2.getId());
        assertEquals(1,walletHistoryRepository.findAll().size());
    }
}