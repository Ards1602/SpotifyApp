package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_playlist")
public class Playlist {
    @Id
    @GenericGenerator(name = "playlist_id",strategy = "uuid")
    @GeneratedValue(generator = "playlist_id")
    private String id;
    private String name;
    @Column(name = "is_public")
    @JsonProperty
    private Boolean isPublic;

    @ManyToMany(mappedBy = "playlists")
    @JsonIgnoreProperties("playlists")
    private List<Song> songs=new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "id_author",nullable = true)
    @JsonIgnoreProperties("playlists")
    private Account author;


    public Playlist(String name, Boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    public Playlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(id, playlist.id) &&
                Objects.equals(name, playlist.name) &&
                Objects.equals(isPublic, playlist.isPublic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isPublic);
    }
}
