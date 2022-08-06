package com.pedrofernandes.pedro_backend.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.domain.Usuario;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ComentarioDTO {

    private Usuario autor;

    private Long imagemId;

    private String comentario;

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Long getImagemId() {
        return imagemId;
    }

    public void setImagemId(Long imagemId) {
        this.imagemId = imagemId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
