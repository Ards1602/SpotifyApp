package com.enigma.spotify.service;

import com.enigma.spotify.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    public Profile saveProfile(Profile profile);
    public Profile getProfile(String id);
    public Page<Profile> getAllProfile(Pageable pageable);
    public void deleteProfile(String id);
}
