package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import com.pedrofernandes.pedro_backend.repository.AmbienteRepository;
import com.pedrofernandes.pedro_backend.repository.UsuarioRepository;
import com.pedrofernandes.pedro_backend.service.dto.AlterarSenhaDTO;
import com.pedrofernandes.pedro_backend.service.dto.CredenciaisDTO;
import com.pedrofernandes.pedro_backend.service.dto.UsuarioDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmbienteService ambienteService;

    @Autowired
    AmbienteRepository ambienteRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Usuario save(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(pe.encode(usuario.getSenha()));
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

    public ResponseEntity<Void> login(CredenciaisDTO creds) {
        Optional<Usuario> usuario = usuarioRepository.procurarUsuario(creds.getLogin());
        if(usuario.isPresent()){
            Usuario usuarioLogado = usuario.get();
            if (pe.matches(creds.getSenha(), usuarioLogado.getSenha())) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
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

    public ResponseEntity<Void> esqueciMinhaSenha(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login).orElse(null);
        if(usuario == null || usuario.getEmail() == null){
            return ResponseEntity.badRequest().build();
        }

        String senhaNova = gerarSenha();

        usuario.setSenha(pe.encode(senhaNova));
        usuarioRepository.save(usuario);

        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(usuario.getEmail());
            helper.setSubject("Nova senha gerada para Galeria de Momentos");
            helper.setText("<p>A nova senha gerada é: "+senhaNova+"</p>", true);
            mailSender.send(mail);

            ResponseEntity.ok(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(null);
    }

    private static String gerarSenha(){
        String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z","+","-","/","*","_","!","@","$","%","&"};

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();

    }

    public ResponseEntity<Void> alterarSenha(AlterarSenhaDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.getLogin()).orElse(null);
        if(usuario == null){
            return ResponseEntity.badRequest().build();
        }

        if (!pe.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            return ResponseEntity.badRequest().build();
        }

        usuario.setSenha(pe.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
