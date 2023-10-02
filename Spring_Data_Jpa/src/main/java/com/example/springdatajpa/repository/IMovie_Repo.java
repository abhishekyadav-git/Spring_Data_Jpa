package com.example.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springdatajpa.entity.Movie;

public interface IMovie_Repo extends CrudRepository<Movie, Integer> {
 // will generate a inmemory proxy class with jpa style implemented methods internally which make use of for
 // persisting data into db	
}
