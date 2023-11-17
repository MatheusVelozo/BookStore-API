package com.velozo.bookstore.service;

import com.velozo.bookstore.Dtos.CategoriaDTO;
import com.velozo.bookstore.domain.Categoria;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.LivroRepository;
import com.velozo.bookstore.resource.CategoriaResource;
import com.velozo.bookstore.resource.LivroResource;
import com.velozo.bookstore.service.exceptions.DataIntegrityViolationException;
import com.velozo.bookstore.service.exceptions.ObjectNotFoundException;
import com.velozo.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private LivroRepository livroRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        Categoria categoria = obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
        List<Livro> livros = livroRepository.findAll();
        for (Livro livro : livros) {
            livro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LivroResource.class).findById(livro.getId())).withRel("Detalhes sobre o livro: "));
        }
        categoria.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaResource.class).findAll()).withRel("Voltar a categorias."));
            return categoria;
    }

    public List<Categoria> findAll() {
        List<Categoria> categoriaList = repository.findAll();
        List<Livro> livros = livroRepository.findAll();
        for (Livro livro : livros) {
            livro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LivroResource.class).findById(livro.getId())).withRel("Detalhes sobre o livro: "));
        }
        return categoriaList;
    }

    public Categoria create(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Integer id, CategoriaDTO objDTO) {
        Categoria obj = findById(id);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Categoria não pode ser deletada! Possui livros associados.");
        }
    }
}
