package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.repository.ImagemRepository;
import com.pedrofernandes.pedro_backend.service.dto.ImagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository repo;

    @Autowired
    private AmbienteService ambienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private S3Service s3Service;


    @Value("${img.prefix}")
    private String prefix;

    @Value("${img.size}")
    private Integer size;

    public Imagem save(ImagemDTO imagemDTO){
        Imagem img = new Imagem();
        img.setLegenda(imagemDTO.getLegenda());
        img.setAutor(usuarioService.findById(imagemDTO.getAutorId()).get());
        img.setAmbiente(ambienteService.findById(imagemDTO.getAmbienteId()).get());
        img.setInstante(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")));
        return repo.save(img);
    }

    public Imagem update(Imagem imagem){
        return repo.save(imagem);
    }

    public void delete(Long id){
        Optional<Imagem> img = repo.findById(id);
        if(img.isPresent()) {
            img.get().getAmbiente().getImagens().remove(img.get());
            img.get().getAutor().getImagens().remove(img.get());
            repo.delete(img.get());
            String fileName = prefix + img.get().getId() + ".jpg";
            s3Service.deleteFile(fileName);
        }else{
            throw new FileException("Arquivo não encontrado");
        }
    }

    public List<Imagem> findAll(){
        return repo.findAll();
    }

    public Optional<List<Imagem>> findAllByAmbiente(Long id){
        Ambiente ambiente = ambienteService.findById(id).get();
        return repo.findAllByAmbiente(ambiente);
    }

    public Optional<Imagem> findById(Long id){
        return repo.findById(id);
    }

    public URI uploadImagem(MultipartFile file, Long imgId){
        BufferedImage jpgImage = imgService.getJpgImageFromFile(file);
        jpgImage = imgService.resize(jpgImage, size);

        String fileName = prefix + imgId + ".jpg";

        return s3Service.uploadFile(imgService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
