package com.Sigma.Sigma.infrastructure.repository;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// todos os metodos do projeto.

@Component
public class MateriaRepositoryImpl implements MateriaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Materia salvar(Materia materia) {
        return manager.merge(materia);
    }

    @Transactional
    public Materia PorId(Long id) {
        return manager.find(Materia.class, id);
    }

    @Transactional
    public void remover(Long materiaId) {
        Materia materia = PorId(materiaId);
        if (materia != null) {
            manager.remove(materia);
        }
    }

    public List<Materia> todos() {
        return manager.createNamedQuery("Materia.findAll", Materia.class)
                .getResultList();
    }
}
