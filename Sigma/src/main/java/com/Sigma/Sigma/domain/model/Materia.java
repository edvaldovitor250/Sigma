package com.Sigma.Sigma.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
public class Materia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome_materia")
    public String nome;

    @Column(name = "numeros_horas")
    public int Horas;

    public Materia() {

    }
}
