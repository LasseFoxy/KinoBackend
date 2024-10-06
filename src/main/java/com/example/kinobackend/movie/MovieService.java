package com.example.kinobackend.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;

    // Directory where images will be stored
    private final String imageDirectory = "path/to/your/image/directory"; // Set your desired path

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(int id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie, MultipartFile imageFile) throws IOException {
        // Logic to save the image file and get its path
        String imagePath = saveImage(imageFile);  // Implement this method based on your requirements
        movie.setMovieImage(imagePath);  // Use setMovieImage to set the image path
        return movieRepository.save(movie);
    }

    public Optional<Object> updateMovie(int id, Movie updatedMovie, MultipartFile image) {
        return movieRepository.findById(id).map(movie -> {
            updatedMovie.setMovieId(id);

            // Handle image upload
            try {
                if (image != null && !image.isEmpty()) {
                    String imagePath = saveImage(image);
                    updatedMovie.setMovieImage(imagePath); // Update the image path if a new image is uploaded
                } else {
                    updatedMovie.setMovieImage(movie.getMovieImage()); // Retain the old image if no new image is uploaded
                }
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
                // Handle error accordingly; here we can return empty optional to signal the failure
                return Optional.empty();
            }

            return movieRepository.save(updatedMovie);
        });
    }

    public boolean deleteMovie(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
            return true;
        }
        return false;
    }

    // Method to save image and return its path
    private String saveImage(MultipartFile image) throws IOException {
        // Create the directory if it doesn't exist
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the image file
        String imagePath = directory + File.separator + image.getOriginalFilename();
        Path path = Paths.get(imagePath);
        Files.write(path, image.getBytes());

        return imagePath; // Return the path of the saved image
    }
}
