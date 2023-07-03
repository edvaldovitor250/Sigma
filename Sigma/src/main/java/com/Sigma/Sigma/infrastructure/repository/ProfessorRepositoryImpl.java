package com.Sigma.Sigma.infrastructure.repository;

import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProfessorRepositoryImpl implements ProfessorRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Professor salvar(Professor professor) {
        return manager.merge(professor);
    }

    @Override
    @Transactional
    public Professor PorId(Long id) {
        return manager.find(Professor.class, id);
    }

    @Override
    @Transactional
    public void remover(Long professorId) {
        Professor professor = PorId(professorId);
        if (professor != null) {
            manager.remove(professor);
        }
    }

    @Override
    public List<Professor> todos() {
        return manager.createNamedQuery("Professor.findAll", Professor.class)
                .getResultList();
    }
}
