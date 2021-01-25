package com.enigma.spotify.entity;


import com.enigma.spotify.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mst_profile")
public class Profile {
    @Id
    @GenericGenerator(name = "profile_id",strategy = "uuid")
    @GeneratedValue(generator = "profile_id")
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;

    @JsonProperty
    private Gender gender;
    private String email;
    private String phone;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String location;

    @OneToOne(mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private Account account;

    public Profile(String firstName, String middleName, String lastName, Gender gender, String email, String phone, String location) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleNam) {
        this.middleName = middleNam;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                Objects.equals(firstName, profile.firstName) &&
                Objects.equals(middleName, profile.middleName) &&
                Objects.equals(lastName, profile.lastName) &&
                gender == profile.gender &&
                Objects.equals(email, profile.email) &&
                Objects.equals(phone, profile.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, gender, email, phone);
    }
}
