package com.example.springdatajpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Help generate required getter and setter and other methods, uses lombok
@Entity // will map this entity class with db table
@Table(name = "MOVIE_T") // To provide table name specifically or else will use class name
@AllArgsConstructor // provides a all arguments constructor
@NoArgsConstructor // provides no arguments constructor
public class Movie implements Serializable { // converts java obj into bits and bytes
	//(post that can send it over network or write it into a fie) 
	@Id // makes the property identity property and maps with primary key of mapped table
	@GeneratedValue(strategy = GenerationType.AUTO) // signifies id will be auto generated
	@Column(name="movieId") // to provide specific column names and limit length but only in case of string
	private Integer movieId;
	@Column(name="movieName", length=20)
	private String movieName;
	@Column(name="year", length=4)
	private String year;
	@Column(name="rating")
	private Float rating;
	
	
}
