package com.pedrofernandes.pedro_backend.repository;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Comentario;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

    Optional<List<Ambiente>> findAllByUsuarios(Usuario usuario);
}
