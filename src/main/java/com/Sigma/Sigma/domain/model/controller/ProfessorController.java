package com.Sigma.Sigma.domain.model.controller;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
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
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {


    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping()
    public List<Professor> todos() {
        return professorRepository.findAll();
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<Professor> porId(@PathVariable Long professorId) {
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
            return ResponseEntity.ok(professor);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor adicionar(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long professorId,
                                               @RequestBody Professor professor) {
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        if (professorOptional.isPresent()) {
            Professor professorAtual = professorOptional.get();
            BeanUtils.copyProperties(professor, professorAtual, "id");

            professorAtual = professorRepository.save(professorAtual);
            return ResponseEntity.ok(professorAtual);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{professorId}")
    public ResponseEntity<Professor> remover(@PathVariable Long professorId) {
        try {
            Optional<Professor> professorOptional = professorRepository.findById(professorId);

            if (professorOptional.isPresent()) {
                Professor professor = professorOptional.get();
                professorRepository.delete(professor);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



    @PatchMapping("/{professorId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long professorId,
                                              @RequestBody Map<String, Object> campos) {
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        if (professorOptional.isPresent()) {
            Professor professorAtual = professorOptional.get();
            // Implemente corretamente o método merge para mesclar os campos atualizados em professorAtual
            merge(campos, professorAtual);

            return atualizar(professorId, professorAtual);
        }

        return ResponseEntity.notFound().build();
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

    @GetMapping("/professor/exists")
    public boolean professorSeExist(String nome) {
        return professorRepository.existsByNome(nome);
    }

    @GetMapping("/professor/por-nome")
    public List<Professor> professors(String nome) {
        return professorRepository.findByNomeContaining(nome);
    }

}
