package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.service.AmbienteService;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteDTO;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteVisualizarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ambientes")
@CrossOrigin("*")
public class AmbienteResource {

    @Autowired
    private AmbienteService ambienteService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AmbienteDTO ambienteDto){
        return ambienteService.save(ambienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ambienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Ambiente>> findAllByUsuario(@PathVariable Long id){
        return new ResponseEntity(ambienteService.findAllByUsuario(id), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AmbienteVisualizarDTO> getById(@PathVariable Long id){
        return new ResponseEntity(ambienteService.getById(id), HttpStatus.OK);
    }

}
