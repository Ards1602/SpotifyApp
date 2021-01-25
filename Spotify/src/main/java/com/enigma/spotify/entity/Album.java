package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_album")
public class Album {
    @Id
    @GenericGenerator(name = "album_id",strategy = "uuid")
    @GeneratedValue(generator = "album_id")
    private String id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Double discount;
    private String image;

    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties("album")
    private List<Song> songs=new ArrayList<>();

    public Album(String title, String description, Integer releaseYear, Double discount) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.discount = discount;
    }

    public Album() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) &&
                Objects.equals(title, album.title) &&
                Objects.equals(description, album.description) &&
                Objects.equals(releaseYear, album.releaseYear) &&
                Objects.equals(discount, album.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseYear, discount);
    }
}
