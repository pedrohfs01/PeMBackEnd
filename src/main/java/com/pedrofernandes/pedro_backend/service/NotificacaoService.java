package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Notificacao;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.repository.NotificacaoRepository;
import com.pedrofernandes.pedro_backend.service.dto.NotificacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    NotificacaoRepository notificacaoRepository;

    public List<Notificacao> findAllByUsuario(Long id) {
        Usuario usuario = usuarioService.findById(id).get();
        return notificacaoRepository.findAllByUsuarioNotificadoId(usuario.getId());
    }

    public Notificacao save(NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        notificacao.setCriador(usuarioService.findById(notificacaoDTO.getCriadorId()).get());
        notificacao.setUsuarioNotificado(usuarioService.findById(notificacaoDTO.getUsuarioNotificadoId()).get());
        notificacao.setDescricao(notificacaoDTO.getDescricao());
        notificacao.setAceitavel(notificacaoDTO.getAceitavel());
        notificacao.setIdObjeto(notificacaoDTO.getIdObjeto());
        notificacao.setTipoNotificacao(notificacaoDTO.getTipoNotificacao());
        return notificacaoRepository.save(notificacao);
    }

    public void delete(Long id) throws Exception {
        Optional<Notificacao> notificacao = notificacaoRepository.findById(id);
        if(notificacao.isPresent()){
            notificacaoRepository.delete(notificacao.get());
        }else{
            throw new Exception("Notificação inexistente");
        }
    }

    public Boolean verificarSeNotificacaoExiste(NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = notificacaoRepository.findByIdObjetoAndTipoNotificacaoAndUsuarioNotificadoId(notificacaoDTO.getIdObjeto(), notificacaoDTO.getTipoNotificacao(), notificacaoDTO.getUsuarioNotificadoId());

        if(notificacao == null)
            return false;

        return true;
    }
}
