package com.pedrofernandes.pedro_backend.service.dto;

public class ComentarioDTO {

    private Long autorId;

    private Long imagemId;

    private String comentario;

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
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
