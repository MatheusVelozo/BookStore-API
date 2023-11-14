package com.velozo.bookstore.Dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(@NotBlank String nome,@NotBlank String descricao) {
}