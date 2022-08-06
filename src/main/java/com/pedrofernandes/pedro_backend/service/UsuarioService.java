package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.repository.AmbienteRepository;
import com.pedrofernandes.pedro_backend.repository.UsuarioRepository;
import com.pedrofernandes.pedro_backend.service.dto.CredenciaisDTO;
import com.pedrofernandes.pedro_backend.service.dto.UsuarioDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmbienteService ambienteService;

    @Autowired
    AmbienteRepository ambienteRepository;

    public Usuario save(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean login(CredenciaisDTO creds) {
        Optional<Usuario> usuario = usuarioRepository.procurarUsuario(creds.getLogin(), creds.getSenha());
        return usuario.isPresent();
    }

    public Optional<List<Usuario>> findAllByAmbiente(Long id) {
        Ambiente ambiente = ambienteService.findById(id).get();
        return usuarioRepository.findAllByAmbientes(ambiente);
    }

    public Optional<List<Usuario>> findAllByNome(String nome){
        Optional<List<Usuario>> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        return usuarios;
    }

    public void addUsuarioInAmbiente(Long idAmbiente, Usuario usuario) {
        Ambiente ambiente = ambienteService.findById(idAmbiente).get();
        Usuario usuarioSave = usuarioRepository.getOne(usuario.getId());
        if(!usuarioSave.getAmbientes().contains(ambiente)){
            usuarioSave.getAmbientes().add(ambiente);
            ambiente.getUsuarios().add(usuarioSave);
            ambienteRepository.save(ambiente);
            usuarioRepository.save(usuarioSave);
        }
    }

    public void removeUsuarioInAmbiente(Long idAmbiente, Usuario usuario){
        Ambiente ambiente = ambienteService.findById(idAmbiente).get();
        Usuario usuarioSave = usuarioRepository.findById(usuario.getId()).get();
        usuarioSave.removeAmbiente(ambiente);
        usuarioRepository.save(usuarioSave);
    }
}
