package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    @Autowired
    ProfileService service;

    @PostMapping("/profile")
    public Profile saveProfile(@RequestBody Profile profile){
        return service.saveProfile(profile);
    }

    @GetMapping("/profile/{id}")
    public Profile getProfileById(@PathVariable String id){
        return service.getProfile(id);
    }

    @GetMapping("/profile")
    public Page<Profile> getAllProfile(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.getAllProfile(pageable);
    }

    @DeleteMapping("/profile/{id}")
    public void deleteProfile(@PathVariable String id){
        service.deleteProfile(id);
    }


}

