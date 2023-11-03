package com.Sigma.Sigma.api.controller;

import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import com.Sigma.Sigma.service.CadastroMateriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    public MateriaController(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Autowired
    private CadastroMateriaService cadastroMateria;

    @GetMapping
    public List<Materia> listar(){
        return  materiaRepository.findAll();
    }

    @GetMapping("/{materiaId}")
    public ResponseEntity<Materia> buscar(@PathVariable Long materiaId) {
        Optional<Materia> materia = materiaRepository.findById(materiaId);

        if (materia.isPresent()) {
            return ResponseEntity.ok(materia.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Materia materia) {
        try {
            materia = cadastroMateria.salvar(materia);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(materia);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{materiaId}")
    public ResponseEntity<?> atualizar(@PathVariable Long materiaId,
                                       @RequestBody @Valid Materia materia) {
        try {
            Optional<Materia> materiaAtual = materiaRepository.findById(materiaId);

            if (materiaAtual.isPresent()) {
                BeanUtils.copyProperties(materia, materiaAtual.get(), "id");

                materiaAtual = Optional.ofNullable(cadastroMateria.salvar(materiaAtual.get()));
                return ResponseEntity.ok(materiaAtual.get());
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{materiaId}")
    public ResponseEntity<Materia> remover(@PathVariable Long materiaId) {
        try {
            cadastroMateria.excluir(materiaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



}
