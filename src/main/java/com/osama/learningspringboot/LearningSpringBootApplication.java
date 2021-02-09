package com.osama.learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class LearningSpringBootApplication {


	public static void main(String[] args) {

		/*
		 * Database: MySQL
		 * Checking Database Connection */
		try{
			System.out.println("Connecting to the database...");
			String jdbcUrl = "jdbc:mysql://localhost:3306/user?allowPublicKeyRetrieval=true&useSSL=false";
			Connection connection = DriverManager.getConnection(jdbcUrl, "root", "osamakhan");
			System.out.println("Connected to the Database!");
		} catch(Exception exp) {
			exp.printStackTrace();
		}


		/*
		* Running the Application */
		SpringApplication.run(LearningSpringBootApplication.class, args);
	}

}