package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.repository.AmbienteRepository;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteDTO;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteVisualizarDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ResponseEntity<Void> save(AmbienteDTO ambienteDTO){
        Ambiente ambiente = new Ambiente();
        BeanUtils.copyProperties(ambienteDTO, ambiente);
        Optional<Usuario> usuario = usuarioService.findById(ambienteDTO.getUsuarioId());
        if(usuario.isPresent()){
            if(usuario.get().getAmbientes().size() >= 10)
                return ResponseEntity.badRequest().build();

            ambiente.getUsuarios().add(usuario.get());
            ambiente.setCriador(usuario.get());
            usuario.get().getAmbientesCriador().add(ambiente);
            usuario.get().getAmbientes().add(ambiente);
            Ambiente ambienteCriado = ambienteRepository.save(ambiente);
            return new ResponseEntity(ambienteCriado, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    public Optional<Ambiente> findById(Long id){
        return ambienteRepository.findById(id);
    }

    public void delete(Long id){
        Optional<Ambiente> ambiente = ambienteRepository.findById(id);
        for(Usuario usuario: ambiente.get().getUsuarios()){
            usuario.getAmbientes().remove(ambiente.get());
            usuario.getAmbientesCriador().remove(ambiente.get());
        }
        ambienteRepository.delete(ambiente.get());
    }

    public Optional<List<Ambiente>> findAllByUsuario(Long id){
        Usuario usuario = usuarioService.findById(id).get();
        return ambienteRepository.findAllByUsuarios(usuario);
    }

    public AmbienteVisualizarDTO getById(Long id) {
        Optional<Ambiente> ambiente = ambienteRepository.findById(id);
        AmbienteVisualizarDTO ambienteDTO = new AmbienteVisualizarDTO();
        if(ambiente.isPresent()){
            Ambiente newAmbiente = ambiente.get();
            ambienteDTO.setId(newAmbiente.getId());
            ambienteDTO.setCriador(newAmbiente.getCriador());
            ambienteDTO.setDescricao(newAmbiente.getDescricao());
            ambienteDTO.setNome(newAmbiente.getNome());
            ambienteDTO.setUsuarios(newAmbiente.getUsuarios());
        }

        return ambienteDTO;
    }
}
