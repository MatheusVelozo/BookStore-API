package com.velozo.bookstore.service;

import com.velozo.bookstore.domain.Categoria;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.CategoriaRepository;
import com.velozo.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LivroRepository livroRepository;
    public void instanciaBaseDeDados() {
        Categoria cat1 = new Categoria(null, "Informática", "Livros de TI");
        Categoria cat2 = new Categoria(null, "Ficção", "Livros de Ficção");
        Categoria cat3 = new Categoria(null, "Biografias", "Biografias");

        Livro l1 = new Livro(null, "Clean code", "Robert Martin", "Loren ipsun", cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Louis", "Loren ipsun", cat1);
        Livro l3 = new Livro(null, "The tima machine", "Martin", "Loren ipsun", cat1);
        Livro l4 = new Livro(null, "The war of the worlds", "Robert", "Loren ipsun", cat2);
        Livro l5 = new Livro(null, "I, Robot", "Isaac Asimov", "Loren ipsun", cat2);

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat1.getLivros().addAll(Arrays.asList(l3, l4, l5));

        this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3));
        this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
    }
}
