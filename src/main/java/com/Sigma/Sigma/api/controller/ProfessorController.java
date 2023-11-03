package com.Sigma.Sigma.api.controller;

import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import com.Sigma.Sigma.service.CadastroMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository  professorRepository;

    @Autowired
    private CadastroMateriaService cadastroMateria;


    @GetMapping
    public List<Professor> listar(){
        return  professorRepository.findAll();
    }

    @PutMapping("/{id}")
    public Professor atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new EntidadeEmUsoException(String.valueOf(id)));

        professorExistente.setNome(professor.getNome());

        return professorRepository.save(professorExistente);
    }


    @PostMapping("/criar")
    public Professor criarProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @DeleteMapping("/remover")
    public void apagar(@RequestBody Professor professor) {
        Professor professorExistente = professorRepository.findById(professor.getId())
                .orElseThrow(() -> new EntidadeEmUsoException("Professor não encontrado com o ID: " + professor.getId()));
        professorRepository.delete(professorExistente);
    }


}
