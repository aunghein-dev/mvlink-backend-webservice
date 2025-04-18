package com.minidevtechcom.myanHomeLabBackend.controller;

import com.minidevtechcom.myanHomeLabBackend.model.Movie;
import com.minidevtechcom.myanHomeLabBackend.service.MovieService;
import com.minidevtechcom.myanHomeLabBackend.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mapi/")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Uncomment or modify this line to enable CORS
public class MovieController {

    @Autowired
    private MovieService service;

    // GET: Retrieve all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = service.getAllMovies();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    // GET: Retrieve a specific movie by TMDB ID
    @GetMapping("movies/{tmdbId}")
    public ResponseEntity<Movie> getMovieByTmdbId(@PathVariable int tmdbId) {
        Movie movie = service.getMovieByTmdbId(tmdbId);

        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET: Retrieve more secured sharedLink by TMDB ID
    @GetMapping("/secure-movie/{tmdbId}")
    public ResponseEntity<?> getSecureMovieLink(@PathVariable int tmdbId) {
        Movie movie = service.getMovieByTmdbId(tmdbId);
        if (movie == null || movie.getSharedLink() == null || movie.getSharedLink().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie or link found.");
        }
        String encryptedLink = AESUtil.encrypt(movie.getSharedLink(), "p4E!x9z@1Lk#Vm$2RfT8GwQe^YhUjIoP");

        Map<String, String> response = new HashMap<>();
        response.put("safeLink", encryptedLink);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/movies")
    public ResponseEntity<?> createOrUpdateMovie(@RequestBody Movie movie) {
        try {

            Movie existing = service.getMovieByTmdbId(movie.getTmdbId());
            Movie result;

            if (existing != null) {
                result = service.updateMovieById(movie.getTmdbId(), movie);
            } else {
                result = service.save(movie);
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace(); // log the real error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to save or update movie: " + e.getMessage());
        }
    }

    // PUT: Update an existing movie
    @PutMapping("/movies/{tmdbId}")
    public ResponseEntity<?> updateMovieById(@PathVariable int tmdbId, @RequestBody Movie movie) {
        Movie updated = service.updateMovieById(tmdbId, movie);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update movie.");
        }

    }

    @DeleteMapping("/movies/{tmdbId}")
    public ResponseEntity<?> deleteMovieById(@PathVariable int tmdbId) {
        try {
            service.deleteMovieById(tmdbId);
            return ResponseEntity.ok("Movie deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete movie.");
        }
    }
}
