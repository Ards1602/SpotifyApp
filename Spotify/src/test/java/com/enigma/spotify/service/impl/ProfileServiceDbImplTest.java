package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.enums.Gender;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.ProfileRepository;
import com.enigma.spotify.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileServiceDbImplTest {

    @Autowired
    ProfileService profileServiceDB;

    @Autowired
    ProfileRepository profileRepository;

    @BeforeEach
    public void cleanUp(){
        profileRepository.deleteAll();
    }


    @Test
    void saveProfile_shouldAdd_1Data_inDB_whenProfileSaved() {
        Profile profile=new Profile("maul","a","na", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        profileServiceDB.saveProfile(profile);
        assertEquals(1,profileRepository.findAll().size());
    }

    @Test
    void getProfile_shouldGetProfile_whenGivenCorrectId() {
        Profile profile=new Profile("maul","a","na", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        Profile profile2=new Profile("lu","hu","ng", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        profileRepository.save(profile);
        profileRepository.save(profile2);
        assertEquals(profile2,profileServiceDB.getProfile(profile2.getId()));

    }

    @Test
    void getProfile_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            profileServiceDB.getProfile("asdasd");
        });
    }

    @Test
    void getAllProfile_shouldBe_2InDB_whenDataInDBIs_2() {
        Profile profile=new Profile("maul","a","na", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        Profile profile2=new Profile("lu","hu","ng", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        profileRepository.save(profile);
        profileRepository.save(profile2);
        assertEquals(2,profileServiceDB.getAllProfile(PageRequest.of(0,5)).getTotalElements());

    }

    @Test
    void deleteProfile_shouldDelete_1Data_inDB_whenProfileDeleted() {
        Profile profile=new Profile("maul","a","na", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        Profile profile2=new Profile("lu","hu","ng", Gender.MALE,"maul@gmail.com"
                ,"089345","disini");
        profileRepository.save(profile);
        profileRepository.save(profile2);
        profileServiceDB.deleteProfile(profile2.getId());
        assertEquals(1,profileRepository.findAll().size());
    }
}