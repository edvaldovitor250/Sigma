package com.Sigma.Sigma.service;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public Materia salvar(Materia materia) {
        return materiaRepository.save(materia);
    }

    public void excluir(Long materiaId) {
        try {
            materiaRepository.deleteById(materiaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de matéria com código %d", materiaId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Matéria de código %d não pode ser removida, pois está em uso", materiaId));
        }
    }

}
