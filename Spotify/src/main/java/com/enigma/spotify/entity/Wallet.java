package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_wallet")

public class Wallet {
    @Id
    @GenericGenerator(name = "wallet_id",strategy = "uuid")
    @GeneratedValue(generator = "wallet_id")
    private String id;
    private Double balance;

    @Transient
    private Double topup;

    @Transient
    private Double withdrawal;

    @OneToOne
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties("wallet")
    private Account owner;

    @OneToMany(mappedBy = "wallet")
//    @JsonIgnoreProperties("wallet")
    @JsonIgnore
    private List<Transaction> transactions=new ArrayList<>();

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties("wallet")
    private List<WalletHistory> walletHistorys =new ArrayList<>();

    public Wallet(Double balance) {
        this.balance = balance;
    }

    public Wallet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTopup() {
        return topup;
    }

    public void setTopup(Double topup) {
        this.topup = topup;
    }

    public Double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<WalletHistory> getWalletHistorys() {
        return walletHistorys;
    }

    public void setWalletHistorys(List<WalletHistory> walletHistorys) {
        this.walletHistorys = walletHistorys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) &&
                Objects.equals(balance, wallet.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }
}
