package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO result = movieService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
    public ResponseEntity<Page<MovieCardDTO>> findAll(@RequestParam(value = "genreId", defaultValue = "0") String genreId,
                                                      Pageable pageable) {
        Page<MovieCardDTO> result = movieService.findAll(genreId, pageable);
        return ResponseEntity.ok().body(result);
    }


}
