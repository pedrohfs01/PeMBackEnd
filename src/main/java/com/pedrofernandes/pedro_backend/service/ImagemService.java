package com.pedrofernandes.pedro_backend.service;


import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository repo;

    @Autowired
    private ImgService imgService;

    @Autowired
    private S3Service s3Service;


    @Value("${img.prefix}")
    private String prefix;

    @Value("${img.size}")
    private Integer size;

    public Imagem save(Imagem img){
        img.setInstante(new Date(System.currentTimeMillis()));
        return repo.save(img);
    }

    public void delete(Long id){
        Optional<Imagem> img = repo.findById(id);
        if(img.isPresent()) {
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


    public URI uploadImagem(MultipartFile file, Imagem img){
        BufferedImage jpgImage = imgService.getJpgImageFromFile(file);
        //jpgImage = imgService.cropSquare(jpgImage);
        jpgImage = imgService.resize(jpgImage, size);

        String fileName = prefix + img.getId() + ".jpg";

        return s3Service.uploadFile(imgService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
