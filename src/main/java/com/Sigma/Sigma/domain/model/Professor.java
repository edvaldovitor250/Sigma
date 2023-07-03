package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
// a Classe do projeto Professor
@Entity
@Data
@AllArgsConstructor
@NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome_professor")
    public String nome;

    @Column(name = "horas_trabalhadas")
    public int horas_trabalhadas;

    public Professor() {

    }
}
