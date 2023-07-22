package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Aluno")
@NamedQueries({@NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_aluno")
    private String nome;


    @ManyToMany
    @JoinTable(name = "aluno_materia",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materias = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "aluno_professor",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professores = new ArrayList<>();

    public Aluno(){

    }
}