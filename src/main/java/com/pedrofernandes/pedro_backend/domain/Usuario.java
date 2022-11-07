package com.pedrofernandes.pedro_backend.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    @JsonIgnore
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Imagem> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL)
    private List<Ambiente> ambientesCriador = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_ambiente",
            joinColumns = @JoinColumn(name = "ambiente_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    private List<Ambiente> ambientes = new ArrayList<>();

    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Notificacao> notificacaoCriador = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioNotificado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Notificacao> notificacoes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes.clear();
        if(ambientes != null){
            this.ambientes = ambientes;
        }
    }

    public void addAmbiente(Ambiente ambiente){
        this.ambientes.add(ambiente);
        ambiente.addUsuario(this);
    }

    public void removeAmbiente(Ambiente ambiente){
        this.ambientes.remove(ambiente);
        ambiente.removeUsuario(this);
    }

    public List<Ambiente> getAmbientesCriador() {
        return ambientesCriador;
    }

    public void setAmbientesCriador(List<Ambiente> ambientesCriador) {
        this.ambientesCriador = ambientesCriador;
    }

    public List<Notificacao> getNotificacaoCriador() {
        return notificacaoCriador;
    }

    public void setNotificacaoCriador(List<Notificacao> notificacaoCriador) {
        this.notificacaoCriador = notificacaoCriador;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
