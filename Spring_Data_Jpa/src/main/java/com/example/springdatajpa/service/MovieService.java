package com.example.springdatajpa.service;

import java.util.List;

import com.example.springdatajpa.entity.Movie;


public interface MovieService {
	public String registerMovie(Movie mov);
	public long getMoviesCount();
	public boolean checkIfMovieExists(Integer id);
	public Movie findMovieById(Integer id);
	public Iterable<Movie> findAllMovies();
	public Iterable<Movie> findAllMoviesById(List<Integer> movieIdsList);
	public String doGroupMovieRegistration(List<Movie> moviesList);
	public String updateMovieInfoById(Integer id, String newYear, Float newRating);
	public String updateMovie(Movie movie);
	public String removeMovieById(Integer id);
	public String removeMovie(Movie movie);
	public String removeMoviesById(List<Integer> idsList);
	public String removeAllMovies();
	public String removeAllMoviesInAGivenList(List<Movie> moviesList);
	
}
