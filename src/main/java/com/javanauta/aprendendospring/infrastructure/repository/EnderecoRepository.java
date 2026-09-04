package com.javanauta.aprendendospring.infrastructure.repository;

import com.javanauta.aprendendospring.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Todas entity precisa de um repository
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
