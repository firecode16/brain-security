package com.brain.security.service;

import com.brain.security.model.User;
import com.brain.security.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author firecode16
 */
@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int updateProfile(Long userId, List<MultipartFile> listFile) {
        try {
            Optional<User> existingUser = userRepository.findByUserId(userId);

            if (listFile.size() == 2) {
                byte[] backdropProfile = listFile.get(0).getBytes();
                byte[] avatarProfile = listFile.get(1).getBytes();

                if (!existingUser.isEmpty()) {
                    User user = existingUser.get();
                    user.setBackdropProfile(backdropProfile);
                    user.setAvatarProfile(avatarProfile);
                    userRepository.save(user);
                }
            } else {
                String fileName = listFile.get(0).getOriginalFilename();

                if (fileName != null) {
                    var indexModified = fileName.lastIndexOf("_");

                    if (indexModified != -1) {
                        String baseModified = fileName.substring(fileName.lastIndexOf("_") + 1);
                        int indexPoint = baseModified.lastIndexOf(".");
                        String adjustFileName = baseModified.substring(0, indexPoint).trim();

                        switch (adjustFileName) {
                            case "BACKDROP" -> {
                                byte[] backdropProfile = listFile.get(0).getBytes();

                                if (!existingUser.isEmpty()) {
                                    User user = existingUser.get();
                                    user.setBackdropProfile(backdropProfile);
                                    userRepository.save(user);
                                }
                            }
                            case "AVATAR" -> {
                                byte[] avatarProfile = listFile.get(0).getBytes();

                                if (!existingUser.isEmpty()) {
                                    User user = existingUser.get();
                                    user.setAvatarProfile(avatarProfile);
                                    userRepository.save(user);
                                }
                            }
                            default -> throw new AssertionError();
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }

            return 1;
        } catch (IOException ex) {
            return -1;
        }
    }

    @Transactional(readOnly = true)
    public byte[] getBackdropProfile(Long userId) {
        return userRepository.findBackdropProfileByUserId(userId);
    }

    @Transactional(readOnly = true)
    public byte[] getAvatarProfile(Long userId) {
        return userRepository.findAvatarProfileByUserId(userId);
    }
}
