package com.anjaniy.onlinemedicalstore.services;
import com.anjaniy.onlinemedicalstore.dto.LoginRequest;
import com.anjaniy.onlinemedicalstore.dto.LoginResponse;
import com.anjaniy.onlinemedicalstore.dto.RefreshTokenRequest;
import com.anjaniy.onlinemedicalstore.dto.RegisterRequest;
import com.anjaniy.onlinemedicalstore.models.AppUser;
import com.anjaniy.onlinemedicalstore.models.Role;
import com.anjaniy.onlinemedicalstore.repositories.AppUserRepository;
import com.anjaniy.onlinemedicalstore.repositories.RoleRepository;
import com.anjaniy.onlinemedicalstore.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public void register(RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(registerRequest.getFirstName());
        appUser.setLastName(registerRequest.getLastName());
        appUser.setUserName(registerRequest.getUserName());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setEmail(registerRequest.getEmail());

        Role role = roleRepository.findByName("USER");
        List<Role> roleSet = new ArrayList<>();
        roleSet.add(role);

        if(registerRequest.getEmail().split("@")[1].equals("omsAdmin.com")){
            role = roleRepository.findByName("ADMIN");
            roleSet.add(role);
        }

        appUser.setRoles(roleSet);

        appUserRepository.save(appUser);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return LoginResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(loginRequest.getUserName())
                .build();
    }

    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUserName());
        return LoginResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(refreshTokenRequest.getUserName())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
