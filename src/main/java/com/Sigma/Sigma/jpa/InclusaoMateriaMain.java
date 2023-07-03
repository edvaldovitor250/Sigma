package com.Sigma.Sigma.jpa;

import com.Sigma.Sigma.SigmaApplication;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoMateriaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new
                SpringApplicationBuilder(SigmaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MateriaRepository adicionar = applicationContext.getBean(MateriaRepository.class);

        Materia materia1 = new Materia();
        materia1.setNome("Geografia");
        materia1.setHoras(200);

        materia1 = adicionar.salvar(materia1);

        System.out.print("Materia:" + materia1.getNome());
        System.out.print("Horas::" + materia1.getHoras());





    }
}
