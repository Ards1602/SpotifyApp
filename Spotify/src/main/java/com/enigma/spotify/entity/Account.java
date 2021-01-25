package com.enigma.spotify.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_account")

public class Account {
    @Id
    @GenericGenerator(name = "account_id",strategy = "uuid2")
    @GeneratedValue(generator = "account_id",strategy = GenerationType.IDENTITY)
    private String id;
    @JsonProperty
    private Boolean active;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Playlist> playlists=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "profile_id")
    @JsonIgnoreProperties("account")
    private Profile profile;

    @OneToOne(mappedBy = "owner")
    @JsonIgnoreProperties("owner")
    private Wallet wallet;

    public Account(Boolean isActive) {
        this.active = isActive;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(active, account.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active);
    }
}
