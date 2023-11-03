package com.Sigma.Sigma.api.controller;

import com.Sigma.Sigma.domain.model.Aluno;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.AlunoRepository;
import com.Sigma.Sigma.service.CadastroAlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private CadastroAlunoService cadastroAluno;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoController(CadastroAlunoService cadastroAluno, AlunoRepository alunoRepository) {
        this.cadastroAluno = cadastroAluno;
        this.alunoRepository = alunoRepository;
    }


    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId) {
        Optional<Aluno> aluno = alunoRepository.findById(alunoId);

        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Aluno aluno) {
        try {
            aluno = cadastroAluno.salvar(aluno);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(aluno);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long alunoId,
                                       @RequestBody @Valid Aluno aluno) {
        try {
            Optional<Aluno> alunoAtual = alunoRepository.findById(alunoId);

            if (alunoAtual.isPresent()) {
                BeanUtils.copyProperties(aluno, alunoAtual.get(), "id");

                alunoAtual = Optional.ofNullable(cadastroAluno.salvar(alunoAtual.get()));
                return ResponseEntity.ok(alunoAtual.get());
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<Aluno> remover(@PathVariable Long alunoId) {
        try {
            cadastroAluno.excluir(alunoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}




