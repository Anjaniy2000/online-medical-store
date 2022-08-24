package com.anjaniy.onlinemedicalstore.services;
import com.anjaniy.onlinemedicalstore.dto.AppUserDto;
import com.anjaniy.onlinemedicalstore.models.AppUser;
import com.anjaniy.onlinemedicalstore.repositories.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<AppUserDto> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(appUser -> modelMapper.map(appUser, AppUserDto.class)).collect(Collectors.toList());
    }

    public void deleteAppUser(Long appUserId) {
        appUserRepository.deleteById(appUserId);
    }

    public AppUserDto getAppUser(Long appUserId) {
        return modelMapper.map(appUserRepository.findById(appUserId), AppUserDto.class);
    }
}
