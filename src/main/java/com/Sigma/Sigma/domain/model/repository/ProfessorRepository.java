package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {


    boolean existsByNome(String nome);

    List<Professor> findByNomeContaining(String nome);
}
