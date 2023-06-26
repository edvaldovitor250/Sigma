package com.Sigma.Sigma.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

// a Classe do projeto Materia


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

    @Column(name = "numeros_horas")
    private int horas;

    public Materia() {

    }
}
