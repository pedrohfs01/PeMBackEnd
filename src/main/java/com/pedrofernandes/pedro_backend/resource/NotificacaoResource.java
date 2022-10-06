package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Notificacao;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.service.NotificacaoService;
import com.pedrofernandes.pedro_backend.service.UsuarioService;
import com.pedrofernandes.pedro_backend.service.dto.CredenciaisDTO;
import com.pedrofernandes.pedro_backend.service.dto.NotificacaoDTO;
import com.pedrofernandes.pedro_backend.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificacoes")
@CrossOrigin("*")
public class NotificacaoResource {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody NotificacaoDTO notificacaoDTO){
        notificacaoService.save(notificacaoDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Notificacao>> findAllByUsuario(@PathVariable Long id){
        return new ResponseEntity(notificacaoService.findAllByUsuario(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        notificacaoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verificar-notificacao-existe")
    public ResponseEntity<Boolean> verificarSeNotificacaoExiste(@RequestBody NotificacaoDTO notificacaoDTO){
        return new ResponseEntity(notificacaoService.verificarSeNotificacaoExiste(notificacaoDTO), HttpStatus.OK);
    }

}
