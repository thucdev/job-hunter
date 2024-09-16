package vn.hoidanit.jobhunter.domain.dto;

import java.time.Instant;

import vn.hoidanit.jobhunter.util.constant.Gender;

public class ResUserDTO {

    private long id;
    private String email;
    private String name;
    private Gender gender;
    private Instant createdAt;
    private Instant updatedAt;

    // public ResUserDTO(long id, String email, String name, Gender gender, Instant createdAt, Instant updatedAt) {
    //     this.id = id;
    //     this.email = email;
    //     this.name = name;
    //     this.gender = gender;
    //     this.createdAt = createdAt;
    //     this.updatedAt = updatedAt;
    // }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    

}
