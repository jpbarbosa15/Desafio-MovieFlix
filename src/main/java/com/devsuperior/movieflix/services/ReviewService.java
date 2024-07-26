package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    @Autowired
    private UserService userService;


    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        copyDTOToEntity(dto, entity);
        repository.save(entity);
        return new ReviewDTO(entity);
    }


    private void copyDTOToEntity(ReviewDTO dto, Review entity) {
        entity.setText(dto.getText());
        Movie movie = new Movie();
        movie.setId(dto.getMovieId());
        entity.setMovie(movie);
        UserDTO userDTO = userService.getProfile();
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        entity.setUser(user);
    }
}
