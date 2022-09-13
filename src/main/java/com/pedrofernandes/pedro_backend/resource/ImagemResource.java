package com.pedrofernandes.pedro_backend.resource;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.service.ImagemService;
import com.pedrofernandes.pedro_backend.service.dto.ImagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ImagemResource {


    @Autowired
    ImagemService service;

    @PostMapping("/imagem")
    public ResponseEntity<Void> uploadImagem(@RequestPart("file")List<MultipartFile> files, @RequestPart("imagem") ImagemDTO imagemDTO) throws MalformedURLException, URISyntaxException {
        if(!files.isEmpty()) {
            URI uri = new URI("");
            for(MultipartFile file : files){
                Imagem imagem = service.save(imagemDTO);
                try{
                    uri = service.uploadImagem(file, imagem.getId());
                    imagem.setImageUrl(uri.toString());
                    service.update(imagem);
                }catch(Exception e){
                    service.delete(imagem.getId());
                }
            }
            return ResponseEntity.created(uri).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/imagem/{id}")
    public ResponseEntity<Void> deleteImagem(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/imagem")
    public ResponseEntity<List<Imagem>> findAllImages(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/imagem/ambiente/{id}")
    public ResponseEntity<List<Imagem>> findAllImagesByAmbiente(@PathVariable Long id){
        return new ResponseEntity(service.findAllByAmbiente(id), HttpStatus.OK);
    }
}
