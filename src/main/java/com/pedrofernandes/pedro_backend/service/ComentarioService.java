package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Comentario;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.repository.ComentarioRepository;
import com.pedrofernandes.pedro_backend.service.dto.ComentarioDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ImagemService imagemService;

    public Comentario save(ComentarioDTO comentarioDTO){
        Comentario comentario = new Comentario();
        BeanUtils.copyProperties(comentarioDTO, comentario);
        Imagem imagem = imagemService.findById(comentarioDTO.getImagemId()).get();
        imagem.getComentarios().add(comentario);
        comentario.setImagem(imagem);
        comentario.setInstante(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")));
        return comentarioRepository.save(comentario);
    }

    public void delete(Long id){
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        comentario.get().getImagem().getComentarios().remove(comentario);
        comentarioRepository.delete(comentario.get());
    }
}
