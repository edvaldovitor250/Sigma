package com.Sigma.Sigma.jpa;

import com.Sigma.Sigma.SigmaApplication;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaMateriaMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new
                SpringApplicationBuilder(SigmaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MateriaRepository consultar = applicationContext.getBean(MateriaRepository.class);

        List<Materia> listarMaterias = consultar.todos();

        for (Materia materia : listarMaterias){
            System.out.println(materia.getNome());
        }

    }
}
