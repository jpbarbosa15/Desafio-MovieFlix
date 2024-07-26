package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;


    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> obj = movieRepository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Movie not found!"));
        return new MovieDetailsDTO(entity);
    }

    public Page<MovieCardDTO> findAll(String genreId, Pageable pageable) {
        Long genreIDNumber = Long.parseLong(genreId);
        Page<MovieCardProjection> page = movieRepository.searchByGenre(genreIDNumber, pageable);
        Page<MovieCardDTO> result = page.map(x -> new MovieCardDTO(x));
        return result;
    }
}
