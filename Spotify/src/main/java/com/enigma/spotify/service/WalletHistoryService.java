package com.enigma.spotify.service;

import com.enigma.spotify.entity.WalletHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WalletHistoryService {
    public WalletHistory saveWalletHistory(WalletHistory history);
    public WalletHistory getWalletHistory(String id);
    public Page<WalletHistory> getAllWalletHistory(Pageable pageable);
    public void deleteWalletHistory(String id);
}
