package com.example.kinobackend.movie;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    // Path til at gemme billeder
    private final Path uploadDirectory = Path.of("uploads");

    // Konstruktor (uden @Autowired)
    public ImageService() {
        try {
            // Opretter upload-mappen, hvis den ikke findes
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            e.printStackTrace();  // Logger fejlene, hvis de opstår
        }
    }

    // Metode til at gemme billede på serveren
    public void saveImage(MultipartFile image) throws IOException {
        // Bestem filnavn og sti
        String fileName = image.getOriginalFilename();
        Path targetPath = uploadDirectory.resolve(fileName);

        // Gem filen
        Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
