package com.brain.security.controller;

import com.brain.security.response.UserProfileResponse;
import com.brain.security.service.UserProfileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author firecode16
 */
@RestController
@CrossOrigin("*")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @PutMapping("/profile/update/{userId}")
    public UserProfileResponse update(@PathVariable Long userId, @RequestParam("file") List<MultipartFile> listFile) {
        int result = profileService.updateProfile(userId, listFile);

        if (result == 1) {
            return new UserProfileResponse(HttpStatus.OK.name(), "User profile is updated");
        } else {
            return new UserProfileResponse(HttpStatus.BAD_REQUEST.name(), "An error occurred during the update");
        }
    }

    @GetMapping(value = "/backdropProfile/{userId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getBackdropProfile(@PathVariable Long userId) {
        return profileService.getBackdropProfile(userId);
    }

    @GetMapping(value = "/avatarProfile/{userId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getAvatarProfile(@PathVariable Long userId) {
        return profileService.getAvatarProfile(userId);
    }
}
