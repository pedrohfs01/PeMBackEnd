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
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity(usuarioService.save(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody CredenciaisDTO creds){
        return usuarioService.login(creds);
    }

    @GetMapping("/ambiente/{id}")
    public ResponseEntity<List<Usuario>> getAllUsuarioByAmbiente(@PathVariable Long id){
        return new ResponseEntity(usuarioService.findAllByAmbiente(id), HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<Optional<Usuario>> getUsuarioByLogin(@PathVariable String login){
        return new ResponseEntity(usuarioService.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Usuario>> getAllByNome(@RequestParam("nome") String nome){
        return new ResponseEntity(usuarioService.findAllByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<Usuario> getByLogin(@RequestParam("login") String login){
        return new ResponseEntity(usuarioService.findByLogin(login), HttpStatus.OK);
    }

    @PutMapping("/adicionar-ambiente/{idAmbiente}")
    public void addUsuarioInAmbiente(@PathVariable Long idAmbiente, @RequestBody Usuario usuario){
        usuarioService.addUsuarioInAmbiente(idAmbiente, usuario);
    }

    @PutMapping("/remover-ambiente/{idAmbiente}")
    public void removeUsuarioInAmbiente(@PathVariable Long idAmbiente, @RequestBody Usuario usuario){
        usuarioService.removeUsuarioInAmbiente(idAmbiente, usuario);
    }

}
