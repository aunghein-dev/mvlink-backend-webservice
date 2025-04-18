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
    return repo.findAll();
  }

  public Movie getMovieByTmdbId(int tmdbId) {
    return repo.findById(tmdbId).orElse(null);
  }

  public Movie save(Movie movie) {
    return repo.save(movie);
  }

  public Movie updateMovieById(int tmdbId, Movie movie) {
    Movie existingMovie = repo.findById(tmdbId).orElse(null);
    if (existingMovie != null) {
      existingMovie.setMoiveName(movie.getMoiveName());
      existingMovie.setSharedLink(movie.getSharedLink());
      existingMovie.setSharedProviderName(movie.getSharedProviderName());
      existingMovie.setFileSize(movie.getFileSize());
      existingMovie.setResolutionDesc(movie.getResolutionDesc());
      existingMovie.setLinkUploadDate(movie.getLinkUploadDate());
      existingMovie.setLinkUpdateDate(movie.getLinkUpdateDate());
      return repo.save(existingMovie);
    } else {
      return null;
    }
  }
}
