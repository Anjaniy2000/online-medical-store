package com.anjaniy.onlinemedicalstore.dto;

import com.anjaniy.onlinemedicalstore.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private List<Role> roles;
}
