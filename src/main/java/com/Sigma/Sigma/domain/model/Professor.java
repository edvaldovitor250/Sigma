package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// A Classe do projeto Professor
@Entity
@Data
@AllArgsConstructor
@Table(name = "professor")
@NamedQueries({@NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public Professor() {

    }
}