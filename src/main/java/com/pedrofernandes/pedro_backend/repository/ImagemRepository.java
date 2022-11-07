package com.pedrofernandes.pedro_backend.repository;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    Optional<List<Imagem>> findAllByAmbienteOrderByInstante(Ambiente ambiente);

}
