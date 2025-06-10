package com.naveen.service;

import com.naveen.entity.UserEntity;
import com.naveen.io.ProfileRequest;
import com.naveen.io.ProfileResponse;
import com.naveen.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        UserEntity newProfile = convertToUserEntity(request);
        if(userRepository.existsByEmail(request.getEmail())){
           throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
        }
        newProfile = userRepository.save(newProfile);
        return converToProfileResponse(newProfile);
    }

    private ProfileResponse converToProfileResponse(UserEntity newProfile) {
        return ProfileResponse.builder()
                .userId(newProfile.getUserId())
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }

    private UserEntity convertToUserEntity(ProfileRequest request) {
        return UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .isAccountVerified(false)
                .resetOtpExpireAt(0L)
                .verifyOtp(null)
                .verifyOtpExpireAt(0L)
                .resetOtp(null)
                .build();
    }
}
