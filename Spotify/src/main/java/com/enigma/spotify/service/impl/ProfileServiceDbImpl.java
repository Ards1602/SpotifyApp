package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.ProfileRepository;
import com.enigma.spotify.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceDbImpl implements ProfileService {
    @Autowired
    ProfileRepository repository;

    @Override
    public Profile saveProfile(Profile profile) {
        List<Profile> profileList = repository.findAllByFirstNameEqualsAndMiddleNameEqualsAndLastNameEquals(profile.getFirstName(),profile.getMiddleName(),profile.getLastName());

        if(profileList.size()==0) {
            return repository.save(profile);
        } else throw new ResourceIsExistException(profile.getFirstName()+" "+profile.getMiddleName()+" "+profile.getLastName());
    }

    @Override
    public Profile getProfile(String id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Profile> getAllProfile(Pageable pageable) {
        Page<Profile> profiles = repository.findAll(pageable);
        return profiles;
    }

    @Override
    public void deleteProfile(String id) {
        repository.deleteById(id);
    }
}
