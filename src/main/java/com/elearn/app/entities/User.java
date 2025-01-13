package com.elearn.app.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    private String userId;
    private String name;
    //username = email
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String password;
    private boolean active;
    private Date createAt;
    private boolean emailVerified;
    private boolean smsVerified;
    private String profilePath;
    private String recentOTP;


    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();


    public void assignRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
