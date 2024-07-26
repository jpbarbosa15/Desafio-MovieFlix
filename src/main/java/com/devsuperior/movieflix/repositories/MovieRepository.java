package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


	@Query(nativeQuery = true,value = """
			SELECT tb_movie.id,tb_movie.title,tb_movie.sub_title as SubTitle,tb_movie.movie_year as MovieYear,tb_movie.img_url as ImgUrl
			FROM tb_movie
			INNER JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
			WHERE (:genreID = 0 OR tb_genre.id = :genreID)
			ORDER BY tb_movie.title
			""")
	Page<MovieCardProjection> searchByGenre(Long genreID, Pageable pageable);

}
