package com.velozo.bookstore.resource;

import com.velozo.bookstore.Dtos.CategoriaDTO;
import com.velozo.bookstore.domain.Categoria;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.CategoriaRepository;
import com.velozo.bookstore.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoriaList = service.findAll();
        if (categoriaList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaList);
        }
    }


    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        var Categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDTO,Categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(Categoria));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id,@RequestBody @Valid CategoriaDTO objDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(new Categoria()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
