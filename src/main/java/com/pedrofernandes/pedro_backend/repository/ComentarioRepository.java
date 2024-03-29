package com.pedrofernandes.pedro_backend.repository;

import com.pedrofernandes.pedro_backend.domain.Comentario;
import com.pedrofernandes.pedro_backend.domain.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
