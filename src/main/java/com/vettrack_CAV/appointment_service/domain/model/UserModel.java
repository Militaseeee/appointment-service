package com.vettrack_CAV.appointment_service.domain.model;

public class UserModel {

    private Long id;
    private String username;
    private String password;
    private String identification;
    private Role role;

    public UserModel() {
    }

    public UserModel(Long id, String username, String password, String identification, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identification = identification;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
