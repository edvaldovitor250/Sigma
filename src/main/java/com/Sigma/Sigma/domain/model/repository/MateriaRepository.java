package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// interface com todos os metodos da classe  MateriaRepositoryImpl
public interface MateriaRepository extends JpaRepository<Materia,Long> {

    List<Materia> findByNomeContaining(String nome);

    boolean existsByNome(String nome);


}