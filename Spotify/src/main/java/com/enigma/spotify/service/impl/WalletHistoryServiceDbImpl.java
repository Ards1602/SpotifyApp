package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.WalletHistory;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.WalletHistoryRepository;
import com.enigma.spotify.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WalletHistoryServiceDbImpl implements WalletHistoryService {
    @Autowired
    WalletHistoryRepository repository;

    @Override
    public WalletHistory saveWalletHistory(WalletHistory history) {
        return repository.save(history);
    }

    @Override
    public WalletHistory getWalletHistory(String id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<WalletHistory> getAllWalletHistory(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteWalletHistory(String id) {
        repository.deleteById(id);
    }
}
