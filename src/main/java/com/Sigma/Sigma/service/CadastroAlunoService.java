package com.Sigma.Sigma.service;

import com.Sigma.Sigma.domain.model.Aluno;
import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void excluir(Long alunoId) {
        try {
            alunoRepository.deleteById(alunoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de Aluno com código %d", alunoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Aluno de código %d não pode ser removido, pois está em uso", alunoId));
        }
    }
}

