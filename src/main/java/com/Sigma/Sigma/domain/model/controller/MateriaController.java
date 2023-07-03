package com.Sigma.Sigma.domain.model.controller;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.service.CadastroMateriaService;
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

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private CadastroMateriaService cadastroMateriaService;


    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping()
    public List<Materia> todos() {
        return materiaRepository.todos();
    }

    @GetMapping("/{materiaId}")
    public ResponseEntity<Materia> PorId(@PathVariable Long materiaId) {
        Materia materia = materiaRepository.PorId(materiaId);

        if (materia != null) {
            return ResponseEntity.ok(materia);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Materia adicionar(@RequestBody Materia materia) {
        return cadastroMateriaService.salvar(materia);
    }

    @PutMapping("/{materiaId}")
    public ResponseEntity<Materia> atualizar(@PathVariable Long materiaId,
                                             @RequestBody Materia materia) {
        Materia materiaAtual = materiaRepository.PorId(materiaId);

        if (materiaAtual != null) {
            BeanUtils.copyProperties(materia, materiaAtual, "id");

            materiaAtual = materiaRepository.salvar(materiaAtual);
            return ResponseEntity.ok(materiaAtual);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{materiaId}")
    public ResponseEntity<Materia> remover(@PathVariable Long materiaId) {
        try {
            cadastroMateriaService.excluir(materiaId);
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
        Materia materiaAtual = materiaRepository.PorId(materiaId);

        if (materiaAtual == null) {
            return ResponseEntity.notFound().build();
        }

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
}
