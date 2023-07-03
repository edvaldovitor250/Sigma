package com.Sigma.Sigma.jpa;

import com.Sigma.Sigma.SigmaApplication;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaMateriaMain {

    public static void main(String[] args) {


        ApplicationContext applicationContext = new
                SpringApplicationBuilder(SigmaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MateriaRepository buscar = applicationContext.getBean(MateriaRepository.class);

        Materia materia = buscar.PorId(1L);
        if (materia != null) {
            System.out.println("Nome da Materia: " + materia.getNome());
        } else {
            System.out.println("Materia não encontrada");
        }




    }
}
