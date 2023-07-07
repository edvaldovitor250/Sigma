package com.Sigma.Sigma.domain.model.controller;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
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
@RequestMapping("/materia")
public class MateriaController {


    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping()
    public List<Materia> todos() {
        return materiaRepository.findAll();
    }

    @GetMapping("/{materiaId}")
    public ResponseEntity<Materia> porId(@PathVariable Long materiaId) {
        Optional<Materia> materia = materiaRepository.findById(materiaId);

        if (materia.isPresent()) {
            return ResponseEntity.ok(materia.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Materia adicionar(@RequestBody Materia materia) {
        return materiaRepository.save(materia);
    }

    @PutMapping("/{materiaId}")
    public ResponseEntity<Materia> atualizar(@PathVariable Long materiaId,
                                             @RequestBody Materia materia) {
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);

        if (materiaOptional.isPresent()) {
            Materia materiaAtual = materiaOptional.get();
            BeanUtils.copyProperties(materia, materiaAtual, "id");

            materiaAtual = materiaRepository.save(materiaAtual);
            return ResponseEntity.ok(materiaAtual);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{materiaId}")
    public ResponseEntity<Materia> remover(@PathVariable Long materiaId) {
        try {
            materiaRepository.deleteById(materiaId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @PatchMapping("/{materiaId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long materiaId,
                                              @RequestBody Map<String, Object> campos) {
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaId);

        if (materiaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Materia materiaAtual = materiaOptional.get();
        // Implemente corretamente o método merge para mesclar os campos atualizados em materiaAtual
        merge(campos, materiaAtual);

        return atualizar(materiaId, materiaAtual);
    }


    private void merge(Map<String, Object> camposOrigem, Materia materiaDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Materia materiaOrigem = objectMapper.convertValue(camposOrigem, Materia.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Materia.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, materiaOrigem);

            ReflectionUtils.setField(field, materiaDestino, novoValor);
        });
    }


    @GetMapping("/materia/exists")
    public boolean materiaexists(String nome) {
        return materiaRepository.existsByNome(nome);
    }

    @GetMapping("/materia/por-nome")
    public List<Materia> materiaPorNome(String nome) {
        return materiaRepository.findByNomeContaining(nome);
    }

}
