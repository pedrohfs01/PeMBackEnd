package com.pedrofernandes.pedro_backend.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrofernandes.pedro_backend.domain.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AmbienteVisualizarDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Usuario criador;

    private List<Usuario> usuarios = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
