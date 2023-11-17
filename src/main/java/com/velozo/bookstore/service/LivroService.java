package com.velozo.bookstore.service;

import com.velozo.bookstore.domain.Categoria;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.LivroRepository;
import com.velozo.bookstore.resource.CategoriaResource;
import com.velozo.bookstore.resource.LivroResource;
import com.velozo.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        Optional<Livro> obj = repository.findById(id);
            Livro livro = obj.orElseThrow(() -> new ObjectNotFoundException("Objetvo não encontrado!" + id + ", Tipo: " + Livro.class.getName()));
            livro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaResource.class).findAll()).withRel("Retornar a categorias."));
        return livro;
    }

    public List<Livro> findAll(Integer id_cat) {
        List<Livro> livroList = repository.findAll();
            for (Livro livro : livroList) {
                id_cat = livro.getId();
                livro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LivroResource.class).findById(id_cat)).withRel("Detalhes sobre o livro."));

            }
        return livroList;
    }

    public Livro update(Integer id, Livro obj) {
        Livro newObj = findById(id);
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Livro newObj, Livro obj) {
        newObj.setTitulo(obj.getTitulo());
        newObj.setNome_autor(obj.getNome_autor());
        newObj.setTexto(obj.getTexto());
    }

    public Livro create(Integer id_cat, Livro obj) {
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        Livro obj = findById(id);
        repository.delete(obj);
    }
}
