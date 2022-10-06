package com.pedrofernandes.pedro_backend.repository;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Notificacao;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.domain.enums.TipoNotificacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {


    List<Notificacao> findAllByUsuarioNotificadoId(Long id);

    @Query("SELECT nt FROM Notificacao nt WHERE " +
            " nt.idObjeto = :idObjeto AND " +
            " nt.tipoNotificacao = :tipoNotificacaoEnum AND" +
            " nt.usuarioNotificado.id = :usuarioNotificadoId")
    Notificacao findByIdObjetoAndTipoNotificacaoAndUsuarioNotificadoId(Long idObjeto, TipoNotificacaoEnum tipoNotificacaoEnum, Long usuarioNotificadoId);
}
