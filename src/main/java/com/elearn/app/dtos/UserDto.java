package com.elearn.app.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean active;
    private Date createAt;
    private boolean emailVerified;
    private boolean smsVerified;
    private String profilePath;
    private String recentOTP;

    private Set<RoleDto> roles = new HashSet<>();

}
