package com.pedrofernandes.pedro_backend.repository;

import com.pedrofernandes.pedro_backend.domain.Ambiente;
import com.pedrofernandes.pedro_backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha")
    Optional<Usuario> procurarUsuario(String login, String senha);

    Optional<Usuario> findByLogin(String login);


    Optional<List<Usuario>> findByNomeContainingIgnoreCase(String nome);

    Optional<List<Usuario>> findAllByAmbientes(Ambiente ambiente);
}
