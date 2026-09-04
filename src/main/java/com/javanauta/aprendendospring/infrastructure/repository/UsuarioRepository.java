package com.javanauta.aprendendospring.infrastructure.repository;

import com.javanauta.aprendendospring.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Todas entity precisa de um repository
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // apontar a tabela e o tipo

    boolean existsByEmail(String email); // cript do jpa que verifica o email, é do jpa

    //Utilizada para evitar o retorno de informações nulas
    Optional<Usuario> findByEmail(String email);

    @Transactional // anotaçao obrigatoria para nao dar erro quando deletar
    void deleteByEmail(String email);

}
