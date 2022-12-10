package com.hoaxify.file;

import com.hoaxify.config.AppConfiguration;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    private final AppConfiguration appConfiguration;
    private final Tika tika;

    public FileService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
        this.tika = new Tika();
    }

    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandomName();
        File target = new File(appConfiguration.getUploadPath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);
        byte[] base64encoded = Base64.getDecoder().decode(image);
        outputStream.write(base64encoded);
        outputStream.close();
        return fileName;
    }

    private String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteFile(String oldImageName) {
        try {
            Files.deleteIfExists(Paths.get(appConfiguration.getUploadPath(), oldImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String detectType(String value) {
        byte[] base64encoded = Base64.getDecoder().decode(value);
        return tika.detect(base64encoded);
    }
}
