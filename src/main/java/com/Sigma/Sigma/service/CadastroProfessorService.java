package com.Sigma.Sigma.service;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public void excluir(Long professorId) {
        try {
            professorRepository.deleteById(professorId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de Professor com código %d", professorId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Professor de código %d não pode ser removida, pois está em uso", professorId));
        }
    }


}
