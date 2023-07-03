package com.Sigma.Sigma.domain.model.controller;

import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.service.CadastroProfessorService;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private CadastroProfessorService cadastroProfessorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping()
    public List<Professor> todos() {
        return professorRepository.todos();
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<Professor> PorId(@PathVariable Long professorId) {
        Professor professor = professorRepository.PorId(professorId);

        if (professor != null) {
            return ResponseEntity.ok(professor);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor adicionar(@RequestBody Professor professor) {
        return cadastroProfessorService.salvar(professor);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long professorId,
                                               @RequestBody Professor professor) {
        Professor professorAtual = professorRepository.PorId(professorId);

        if (professorAtual != null) {
            BeanUtils.copyProperties(professor, professorAtual, "id");

            professorAtual = professorRepository.salvar(professorAtual);
            return ResponseEntity.ok(professorAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Professor> remover(@PathVariable Long professorId) {
        try {
            cadastroProfessorService.excluir(professorId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{professorId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long professorId,
                                              @RequestBody Map<String, Object> campos) {
        Professor professorAtual = professorRepository.PorId(professorId);

        if (professorAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, professorAtual);

        return atualizar(professorId, professorAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Professor professorDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Professor professorOrigem = objectMapper.convertValue(camposOrigem, Professor.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Professor.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, professorOrigem);

            ReflectionUtils.setField(field, professorDestino, novoValor);
        });
    }
}
