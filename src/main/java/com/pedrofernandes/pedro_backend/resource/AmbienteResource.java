package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.service.AmbienteService;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteDTO;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteVisualizarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AmbienteResource {

    @Autowired
    private AmbienteService ambienteService;

    @PostMapping("/ambientes")
    public ResponseEntity<Void> save(@RequestBody AmbienteDTO ambienteDto){

        return new ResponseEntity(ambienteService.save(ambienteDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/ambientes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ambienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ambientes/usuario/{id}")
    public ResponseEntity<List<Ambiente>> findAllByUsuario(@PathVariable Long id){
        return new ResponseEntity(ambienteService.findAllByUsuario(id), HttpStatus.OK);
    }


    @GetMapping("/ambientes/{id}")
    public ResponseEntity<AmbienteVisualizarDTO> getById(@PathVariable Long id){
        return new ResponseEntity(ambienteService.getById(id), HttpStatus.OK);
    }

}
