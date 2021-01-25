package com.enigma.spotify.entity;

import com.enigma.spotify.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_artist")
public class Artis {
    @Id
    @GenericGenerator(name = "artist_id",strategy = "uuid")
    @GeneratedValue(generator = "artist_id")
    private String uuid;
    private String name;
    @Column(name = "debut_year")
    private Integer debutYear;
    @JsonProperty
    private Gender gender;
    private String biography;
    private String photo;

    @OneToMany(mappedBy = "artis")
    @JsonIgnoreProperties("artis")
    private List<Song> songs=new ArrayList<>();

    public Artis(String name, Integer debutYear, String biography) {
        this.name = name;
        this.debutYear = debutYear;
        this.biography = biography;
    }

    public Artis() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(Integer debutYear) {
        this.debutYear = debutYear;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        Artis artis = (Artis) o;
        return Objects.equals(uuid, artis.uuid) &&
                Objects.equals(name, artis.name) &&
                Objects.equals(debutYear, artis.debutYear) &&
                gender == artis.gender &&
                Objects.equals(biography, artis.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, debutYear, gender, biography);
    }
}
