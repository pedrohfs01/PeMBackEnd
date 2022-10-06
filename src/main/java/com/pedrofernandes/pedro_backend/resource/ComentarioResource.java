package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Comentario;
import com.pedrofernandes.pedro_backend.service.ComentarioService;
import com.pedrofernandes.pedro_backend.service.dto.ComentarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin("*")
public class ComentarioResource {

    @Autowired
    private ComentarioService comentarioService;


    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity(comentarioService.save(comentarioDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        comentarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
