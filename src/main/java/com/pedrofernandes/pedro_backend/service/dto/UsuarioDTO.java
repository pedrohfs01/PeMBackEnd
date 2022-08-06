package com.pedrofernandes.pedro_backend.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Comentario;
import com.pedrofernandes.pedro_backend.domain.Imagem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private String login;

    private String senha;


    private String nome;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
