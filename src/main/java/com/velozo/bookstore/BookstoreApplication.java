package com.velozo.bookstore;

import com.velozo.bookstore.domain.Categoria;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.CategoriaRepository;
import com.velozo.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	}