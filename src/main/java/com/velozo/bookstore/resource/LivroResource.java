package com.velozo.bookstore.resource;

import com.velozo.bookstore.Dtos.LivroDTO;
import com.velozo.bookstore.domain.Livro;
import com.velozo.bookstore.repositories.LivroRepository;
import com.velozo.bookstore.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    LivroRepository repository;
    @Autowired
    private LivroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(@RequestParam (value = "categoria", defaultValue = "0") Integer id_cat) {
        List<Livro> livrolist = service.findAll(id_cat);
        if (livrolist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(livrolist);
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable @Valid Integer id, @RequestBody LivroDTO livroDTO) {
      //Livro newObj = service.update(id, obj);
      return ResponseEntity.status(HttpStatus.OK).body(repository.save(new Livro()));
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> updatePath(@PathVariable Integer id,@Valid @RequestBody Livro obj) {
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam (value = "categoria", defaultValue = "0")Integer id_cat,@Valid @RequestBody Livro obj) {
        Livro newObj = service.create(id_cat, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
