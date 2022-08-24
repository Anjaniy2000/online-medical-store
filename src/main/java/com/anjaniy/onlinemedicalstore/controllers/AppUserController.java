package com.anjaniy.onlinemedicalstore.controllers;

import com.anjaniy.onlinemedicalstore.dto.AppUserDto;
import com.anjaniy.onlinemedicalstore.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUsers")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllAppUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserDto> getAllAppUsers(){
        return appUserService.getAllAppUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAppUser/{appUserId}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDto getAppUser(@PathVariable("appUserId") Long appUserId){
        return appUserService.getAppUser(appUserId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteAppUser/{appUserId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAppUser(@PathVariable("appUserId") Long appUserId){
        appUserService.deleteAppUser(appUserId);
        return "User Deleted Successfully";
    }
}
