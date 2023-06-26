package com.Sigma.Sigma.infrastructure.repository;

import com.Sigma.Sigma.domain.model.Materia;

import java.util.List;

// interface com todos os metodos da classe  MateriaRepositoryImpl
public interface MateriaRepository {

    List<Materia> todos();

    Materia PorId(Long id);

    Materia salvar(Materia materia);

    void remover(Materia materia);



}
