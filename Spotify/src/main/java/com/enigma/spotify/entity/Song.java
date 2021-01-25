package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_song")
public class Song {
    @Id
    @GenericGenerator(name = "song_id",strategy = "uuid")
    @GeneratedValue(generator = "song_id",strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private Integer releaseYear;
    @JsonIgnore
    private Integer duration;
    private Double price;

    @Transient
    private String length;

    @ManyToOne()
    @JoinColumn(name = "id_genre",nullable = true)
    @JsonIgnoreProperties("songs")
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "id_album",nullable = true)
    @JsonIgnoreProperties("songs")
    private Album album;

    @ManyToOne()
    @JoinColumn(name = "id_artist",nullable = true)
    @JsonIgnoreProperties("songs")
    private Artis artis;

    @ManyToMany()
    @JoinTable(name = "mst_song_has_playlist",joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    @JsonIgnoreProperties("songs")
    private List<Playlist> playlists=new ArrayList<>();

    @OneToMany(mappedBy = "item")
//    @JsonIgnoreProperties("item")
    @JsonIgnore
    private List<Transaction> transactions=new ArrayList<>();

    public Song(String title, Integer releaseYear, Integer duration, Double price) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.price = price;
    }

    public Song() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artis getArtis() {
        return artis;
    }

    public void setArtis(Artis artis) {
        this.artis = artis;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(title, song.title) &&
                Objects.equals(releaseYear, song.releaseYear) &&
                Objects.equals(duration, song.duration) &&
                Objects.equals(price, song.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseYear, duration, price);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", duration=" + duration +
                ", price=" + price +
                ", length='" + length + '\'' +
                '}';
    }
}
