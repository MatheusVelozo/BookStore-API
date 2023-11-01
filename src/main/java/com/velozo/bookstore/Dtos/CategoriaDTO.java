package com.velozo.bookstore.Dtos;

import com.velozo.bookstore.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 5, max = 20, message = "O campo NOME deve ter entre 3 e 20 caracteres.")
    private String nome;
    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 5, max = 50, message = "O campo DESCRIÇÃO deve ter entre 5 e 50 caracteres.")
    private String descricao;

    public CategoriaDTO() {
        super();
    }

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
