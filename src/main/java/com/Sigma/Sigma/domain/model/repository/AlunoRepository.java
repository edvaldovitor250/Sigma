package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    List<Aluno> findByNomeContaining(String nome);

    boolean existsByNome(String nome);

}
