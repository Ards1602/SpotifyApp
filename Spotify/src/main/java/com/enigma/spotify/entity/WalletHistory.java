package com.enigma.spotify.entity;

import com.enigma.spotify.enums.HistoryEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mst_history")

public class WalletHistory {
    @Id
    @GenericGenerator(name = "wallethistory_id",strategy = "uuid")
    @GeneratedValue(generator = "wallethistory_id")
    private String id;

    @JsonProperty
    private HistoryEnum type;
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date trxDate;

    @ManyToOne()
    @JoinColumn(name = "id_wallet",nullable = true)
    @JsonIgnoreProperties("walletHistorys")
    private Wallet wallet;

    public WalletHistory(HistoryEnum type, Double amount) {
        this.type = type;
        this.amount = amount;
    }

    public WalletHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HistoryEnum getType() {
        return type;
    }

    public void setType(HistoryEnum type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletHistory that = (WalletHistory) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount);
    }
}
