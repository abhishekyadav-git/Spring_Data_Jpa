package com.example.springdatajpa.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springdatajpa.entity.Movie;
import com.example.springdatajpa.service.MovieService;

@Component
public class CrudRepoTestRunner implements CommandLineRunner{ 
	// CommandLine Runner interface which provides run method to be implemented and run only once 
	// This is called by spring boot automatically
	@Autowired // service class injection
	private MovieService movieService;

	@Override
	public void run(String... args) throws Exception {
		
		Movie movie = new Movie();
		movie.setMovieName("Avengers");
		movie.setYear("2019");
		movie.setRating(4.5F);
		
		try {
			System.out.println(movieService.registerMovie(movie));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Total Movies Count = "+movieService.getMoviesCount());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			int findId = 2;
			System.out.println("Movie with id = "+findId + (movieService.checkIfMovieExists(findId) ? "is available" : "not available"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			int findId = 1;
			System.out.println("Movie details of = "+findId + "id are " +movieService.findMovieById(findId)); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("All movies details are as follows :");
			for(Movie mov : movieService.findAllMovies()) {
				System.out.println(mov);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(" Movies details with id's 1,2 and 52 are as follows :");
			for(Movie mov : movieService.findAllMoviesById(List.of(1,2,52))) {
				System.out.println(mov);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Movie m1 = new Movie(null,"SpiderMan","2021",3.5f);
			Movie m2 = new Movie(null,"American Sniper","2002",4.8f);
			Movie m3 = new Movie(null,"Joker","2021",4.5f);
			System.out.println(movieService.doGroupMovieRegistration(List.of(m1,m2,m3)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(movieService.updateMovieInfoById(1, "2022", 3.7f));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Movie m1 = new Movie(2,"Scavengers","2023",3.9f);
			System.out.println(movieService.updateMovie(m1));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(movieService.removeMovieById(52));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(movieService.removeMovie(new Movie(2,"Scavengers","2023",3.9f)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(movieService.removeMoviesById(List.of(1053,1054)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Movie m1 = new Movie(1052, "Avengers","2019", 4.5f);
			Movie m2 = new Movie(952,"Avengers","2019", 4.5f);
			System.out.println(movieService.removeAllMoviesInAGivenList(List.of(m1,m2)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(movieService.removeAllMovies());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
