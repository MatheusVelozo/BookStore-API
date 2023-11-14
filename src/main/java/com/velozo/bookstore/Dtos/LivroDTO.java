package com.velozo.bookstore.Dtos;

import jakarta.validation.constraints.NotBlank;

public record LivroDTO(@NotBlank String titulo,@NotBlank String nome_autor,@NotBlank String texto) {

}