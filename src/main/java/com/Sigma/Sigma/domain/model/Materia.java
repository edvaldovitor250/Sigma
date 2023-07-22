package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "materia")
@NamedQueries({@NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")})
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_materia")
    private String nome;

    @Column(nullable = false, name = "numeros_horas")
    private int horas;

    @ManyToMany(mappedBy = "materias")
    private List<Professor> professores = new ArrayList<>();

    @ManyToMany(mappedBy = "materias")
    private List<Aluno> alunos = new ArrayList<>();

    public Materia(){

    }
}