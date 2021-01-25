package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mst_transaction")
public class Transaction {
    @Id
    @GenericGenerator(name = "transcation_id",strategy = "uuid")
    @GeneratedValue(generator = "transcation_id",strategy = GenerationType.IDENTITY)
    private String id;
    private Double amount;
    private Double albumDiscount;

    @Transient
    private String album;

    @Transient
    private String songTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date trxDate;

    @ManyToOne()
    @JoinColumn(name = "id_song",nullable = true)
    @JsonIgnoreProperties("transactions")
//    @JsonIgnore
    private Song item;

    @ManyToOne()
    @JoinColumn(name = "id_wallet",nullable = true)
    @JsonIgnoreProperties("transactions")
//    @JsonIgnore
    private Wallet wallet;

    public Transaction() {
    }

    public Transaction(Double amount, Double albumDiscount, Date trxDate, Song item, Wallet wallet) {
        this.amount = amount;
        this.albumDiscount = albumDiscount;
        this.trxDate = trxDate;
        this.item = item;
        this.wallet = wallet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAlbumDiscount() {
        return albumDiscount;
    }

    public void setAlbumDiscount(Double albumDiscount) {
        this.albumDiscount = albumDiscount;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Song getItem() {
        return item;
    }

    public void setItem(Song item) {
        this.item = item;
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
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(albumDiscount, that.albumDiscount) &&
                Objects.equals(trxDate, that.trxDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, albumDiscount, trxDate);
    }
}

