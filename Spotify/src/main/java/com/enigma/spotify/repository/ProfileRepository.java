package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    public List<Profile> findAllByFirstNameEqualsAndMiddleNameEqualsAndLastNameEquals(String first, String middle, String last);
}
