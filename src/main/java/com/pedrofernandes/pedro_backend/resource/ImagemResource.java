package com.pedrofernandes.pedro_backend.resource;

import com.pedrofernandes.pedro_backend.domain.Imagem;
import com.pedrofernandes.pedro_backend.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ImagemResource {


    @Autowired
    ImagemService service;

    @PostMapping("/imagem")
    public ResponseEntity<Void> uploadImagem(@RequestParam("file")MultipartFile file) throws MalformedURLException {
        if(!file.isEmpty()) {
            Imagem imagem = new Imagem();
            service.save(imagem);

            URI uri = service.uploadImagem(file, imagem);
            imagem.setImageUrl(uri.toString());
            service.save(imagem);
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
}
