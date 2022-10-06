package com.pedrofernandes.pedro_backend.service.dto;

import com.pedrofernandes.pedro_backend.domain.enums.TipoNotificacaoEnum;

public class NotificacaoDTO {

    private Long criadorId;
    private Long usuarioNotificadoId;
    private Boolean aceitavel;
    private String descricao;
    private Long idObjeto;
    private TipoNotificacaoEnum tipoNotificacao;

    public Long getCriadorId() {
        return criadorId;
    }

    public void setCriadorId(Long criadorId) {
        this.criadorId = criadorId;
    }

    public Long getUsuarioNotificadoId() {
        return usuarioNotificadoId;
    }

    public void setUsuarioNotificadoId(Long usuarioNotificadoId) {
        this.usuarioNotificadoId = usuarioNotificadoId;
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

    public Long getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }

    public TipoNotificacaoEnum getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacaoEnum tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }
}
