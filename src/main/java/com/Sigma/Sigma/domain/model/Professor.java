package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// a Classe do projeto Professor
@Entity
@Data
@AllArgsConstructor
@Table(name = "professor")
@NamedQueries({@NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_professor")
    private String nome;

    @ManyToMany
    @JoinTable(name = "professor_materia",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materias = new ArrayList<>();


    @ManyToMany(mappedBy = "professores")
    private List<Aluno> alunos = new ArrayList<>();

    public Professor() {

    }
}