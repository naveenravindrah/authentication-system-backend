package com.naveen.service;

import com.naveen.io.ProfileRequest;
import com.naveen.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
}
