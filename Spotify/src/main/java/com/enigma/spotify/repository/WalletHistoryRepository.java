package com.enigma.spotify.repository;

import com.enigma.spotify.entity.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletHistoryRepository extends JpaRepository<WalletHistory,String> {
}
