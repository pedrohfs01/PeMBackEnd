package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.service.UsuarioService;
import com.pedrofernandes.pedro_backend.service.dto.CredenciaisDTO;
import com.pedrofernandes.pedro_backend.service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<Void> save(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity(usuarioService.save(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<Void> login(@RequestBody CredenciaisDTO creds){
        if(usuarioService.login(creds)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios/ambiente/{id}")
    public ResponseEntity<List<Usuario>> getAllUsuarioByAmbiente(@PathVariable Long id){
        return new ResponseEntity(usuarioService.findAllByAmbiente(id), HttpStatus.OK);
    }

    @GetMapping("/usuarios/login/{login}")
    public ResponseEntity<Optional<Usuario>> getUsuarioByLogin(@PathVariable String login){
        return new ResponseEntity(usuarioService.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping("/usuarios/search")
    public ResponseEntity<List<Usuario>> getAllByNome(@RequestParam("nome") String nome){
        return new ResponseEntity(usuarioService.findAllByNome(nome), HttpStatus.OK);
    }

    @PutMapping("/usuarios/adicionar-ambiente/{idAmbiente}")
    public void addUsuarioInAmbiente(@PathVariable Long idAmbiente, @RequestBody Usuario usuario){
        usuarioService.addUsuarioInAmbiente(idAmbiente, usuario);
    }

    @PutMapping("/usuarios/remover-ambiente/{idAmbiente}")
    public void removeUsuarioInAmbiente(@PathVariable Long idAmbiente, @RequestBody Usuario usuario){
        usuarioService.removeUsuarioInAmbiente(idAmbiente, usuario);
    }

}
