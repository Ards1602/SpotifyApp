package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,String> {
}
