package com.example.springdatajpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdatajpa.entity.Movie;
import com.example.springdatajpa.repository.IMovie_Repo;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired // Inject dynamically generated im memory proxy class 
	private IMovie_Repo movieRepo;

	@Override
	public String registerMovie(Movie mov) {
		System.out.println("In memory proxy class = " +movieRepo.getClass());
		System.out.println("movie Repo Interface's = " +Arrays.toString(movieRepo.getClass().getInterfaces()));
		System.out.println("Movie Before  : "+mov);
		Movie mov1 = movieRepo.save(mov); // to (insert) persist data into db and returns object(with updated properties)
		System.out.println("Movie After Jpa persistence  : "+mov1);
		
		return "Movie : " + mov1.getMovieName() + " registered with movieId = " + mov1.getMovieId();
	}

	@Override
	public long getMoviesCount() {		
		return movieRepo.count();
	}

	@Override
	public boolean checkIfMovieExists(Integer id) {
		return movieRepo.existsById(id);
	}

	@Override
	public Movie findMovieById(Integer id) {
		return movieRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Record not found"));
	}

	@Override
	public Iterable<Movie> findAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public Iterable<Movie> findAllMoviesById(List<Integer> movieIdsList) {
		return movieRepo.findAllById(movieIdsList);
	}

	@Override
	public String doGroupMovieRegistration(List<Movie> moviesList) {
		Iterable<Movie> allMovies = movieRepo.saveAll(moviesList);
		List<Integer> idsList = new ArrayList<>();
		if(allMovies != null && ((List<Movie>)allMovies).size() > 0) {
			for(Movie m : allMovies) {
				idsList.add(m.getMovieId());
			}
		}
		return idsList.toString() + " id's are of the movies registered successfully";
	}

	@Override
	public String updateMovieInfoById(Integer id, String newYear, Float newRating) {
		// first check if the movie we want to update is available or not
		Optional<Movie> opt = movieRepo.findById(id);
		if(opt.isPresent()) { 
			Movie movie = opt.get();
			movie.setRating(newRating);
			movie.setYear(newYear);
			movieRepo.save(movie); // will perform merge operation
			return id + "updated with required info";
		}
		
		return "movie to be updated not found";
	}

	@Override
	public String updateMovie(Movie movie) {
		// first check if the movie we want to update is available or not
				Optional<Movie> opt = movieRepo.findById(movie.getMovieId());
				if(opt.isPresent()) { 
					movieRepo.save(movie); // will perform merge operation
					return movie.getMovieId() + "updated with required info";
				}
				
				return "movie to be updated not found";
	}

	@Override
	public String removeMovieById(Integer id) {
		Optional<Movie> opt = movieRepo.findById(id);
		if(opt.isPresent()) { 
			movieRepo.deleteById(id);
			return "movie with id = " + id + " is deleted successfully";
		}
		return "movie to be deleted not found";
	}

	@Override
	public String removeMovie(Movie movie) {
		Optional<Movie> opt = movieRepo.findById(movie.getMovieId());
		if(opt.isPresent()) { 
			movieRepo.delete(movie);
			return "movie " + movie + " is deleted successfully";
		}
		return "movie to be deleted not found";
	}

	@Override
	public String removeMoviesById(List<Integer> idsList) {
		Iterable<Movie> itr = movieRepo.findAllById(idsList);
		if(idsList.size() == ((List<Movie>)itr).size()) {
			movieRepo.deleteAllById(idsList);
			return "movies with id's " + idsList.toString() + " are deleted successfully";
		}
		return "either some or all movies to be deleted were not found";
	}

	@Override
	public String removeAllMovies() {
		long count = movieRepo.count();
		if(count > 0) {
			movieRepo.deleteAll();
			return "all movies are deleted successfully";
		}
		return "no movie found";
	}

	@Override
	public String removeAllMoviesInAGivenList(List<Movie> moviesList) {
		List<Integer> idsList = new ArrayList<>();
		for(Movie m : moviesList) {
			idsList.add(m.getMovieId());
		}
		Iterable<Movie> itr = movieRepo.findAllById(idsList);
		if(idsList.size() == ((List<Movie>)itr).size()) {
			movieRepo.deleteAll(moviesList);
			return "movies with id's " + idsList.toString() + " are deleted successfully";
		}
		return "either some or all movies to be deleted were not found";
	}

}
