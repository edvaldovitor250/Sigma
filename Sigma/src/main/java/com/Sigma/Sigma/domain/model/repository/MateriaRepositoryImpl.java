package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.infrastructure.repository.MateriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// todos os metodos do projeto.

@Component
public class MateriaRepositoryImpl implements MateriaRepository {
    @PersistenceContext
    private EntityManager maneger;

    @Transactional
    public Materia salvar(Materia materia) {
        return maneger.merge(materia);
    }

    @Transactional
    public Materia PorId(Long id) {

        return maneger.find(Materia.class, id);
    }
    @Transactional
    public void remover(Materia materia) {
        materia = PorId(materia.getId());
        maneger.remove(materia);
    }

    public List<Materia> todos() {
        return maneger.createNamedQuery("Materia.findAll", Materia.class)
                .getResultList();
    }

}
