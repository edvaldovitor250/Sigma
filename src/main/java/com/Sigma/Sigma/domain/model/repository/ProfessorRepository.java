package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.Professor;

import java.util.List;

public interface ProfessorRepository {



    List<Professor> todos();

    Professor PorId(Long id);

    Professor salvar(Professor professor);

    void remover(Long Professor);

}
