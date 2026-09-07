package com.example.movies;

import dao.MovieDAO;
import dto.Movie;

public class InsertData {

    public static void main(String[] args) {

        // Create Movie object
        Movie movie = new Movie(
                2,
                "Toxic",
                "Yash",
                "Rukmini Vasanth"
        );

        // Create DAO object
        MovieDAO dao = new MovieDAO();

        // Insert movie into database
        dao.insertMovie(movie);

        System.out.println("Movie inserted successfully!");
    }
}