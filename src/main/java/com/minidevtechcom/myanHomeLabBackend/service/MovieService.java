package com.minidevtechcom.myanHomeLabBackend.service;

import com.minidevtechcom.myanHomeLabBackend.model.Movie;
import com.minidevtechcom.myanHomeLabBackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;


    public List<Movie> getAllMovies() {
      return  repo.findAll();
    }

    public Movie getMovieByTmdbId(int tmdbId) {
        return repo.findById(tmdbId).orElse(null);
    }
}
