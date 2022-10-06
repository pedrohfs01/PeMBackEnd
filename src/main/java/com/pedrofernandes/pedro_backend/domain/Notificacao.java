package com.pedrofernandes.pedro_backend.domain;

import com.pedrofernandes.pedro_backend.domain.enums.TipoNotificacaoEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="notificacao")
public class Notificacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aceitavel")
    private Boolean aceitavel;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_notificacao")
    @Enumerated(EnumType.ORDINAL)
    private TipoNotificacaoEnum tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "criador_notificacao_id")
    private Usuario criador;

    @ManyToOne
    @JoinColumn(name = "usuario_notificado_id")
    private Usuario usuarioNotificado;

    @Column(name = "id_objeto")
    private Long idObjeto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAceitavel() {
        return aceitavel;
    }

    public void setAceitavel(Boolean aceitavel) {
        this.aceitavel = aceitavel;
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

    public Usuario getUsuarioNotificado() {
        return usuarioNotificado;
    }

    public void setUsuarioNotificado(Usuario usuarioNotificado) {
        this.usuarioNotificado = usuarioNotificado;
    }

    public TipoNotificacaoEnum getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacaoEnum tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public Long getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }
}
