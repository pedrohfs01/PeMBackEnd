package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.repository.AmbienteRepository;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteDTO;
import com.pedrofernandes.pedro_backend.service.dto.AmbienteVisualizarDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Ambiente save(AmbienteDTO ambienteDTO){
        Ambiente ambiente = new Ambiente();
        BeanUtils.copyProperties(ambienteDTO, ambiente);
        Optional<Usuario> usuario = usuarioService.findById(ambienteDTO.getUsuarioId());
        ambiente.getUsuarios().add(usuario.get());
        ambiente.setCriador(usuario.get());
        usuario.get().getAmbientesCriador().add(ambiente);
        usuario.get().getAmbientes().add(ambiente);
        return ambienteRepository.save(ambiente);
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
