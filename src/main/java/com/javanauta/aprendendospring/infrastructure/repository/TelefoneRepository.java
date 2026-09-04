package com.javanauta.aprendendospring.infrastructure.repository;

import com.javanauta.aprendendospring.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Todas entity precisa de um repository
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
