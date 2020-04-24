package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService {

    private final UserService userService;

    @Autowired
    public FileService(UserService userService) {
        this.userService = userService;
    }

    public String uploadImg(MultipartFile img, Path directory) throws IOException {
        try {
            checkDirectoryExist(directory.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!img.isEmpty() && checkImgExpansion(Objects.requireNonNull(img.getOriginalFilename()))) {
            byte[] imgSize = img.getBytes();
            Files.write(Paths.get(directory + File.separator + img.getOriginalFilename()), imgSize);

            return img.getOriginalFilename();
        }

        return null;
    }

    public void setDefaultAvatar() {
        User currentUser = userService.getAnAuthorizedUser();
        if (currentUser.getAvatar() == null || currentUser.getAvatar().equals("")) {
            userService.updateAvatarOfCurrentUser("default_avatar.png");
        }
    }

    private void checkDirectoryExist(String directory) throws IOException {
        Path avatarDirectory = Paths.get(directory);

        if(!Files.exists(avatarDirectory)) {
            Files.createDirectory(avatarDirectory);
        }
    }

    private boolean checkImgExpansion(String filename) {
        return  filename.endsWith("png") ||
                filename.endsWith("jpg") ||
                filename.endsWith("gif");
    }
}
