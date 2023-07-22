package com.Sigma.Sigma.api.controller;

import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import com.Sigma.Sigma.service.CadastroMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
