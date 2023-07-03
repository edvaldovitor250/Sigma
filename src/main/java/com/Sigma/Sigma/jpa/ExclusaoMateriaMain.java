package com.Sigma.Sigma.jpa;

import com.Sigma.Sigma.SigmaApplication;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoMateriaMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new
                SpringApplicationBuilder(SigmaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MateriaRepository materia = applicationContext.getBean(MateriaRepository.class);

        Materia materia1 = new Materia();
        materia1.setId(2L);

        materia.remover(materia1.getId());

    }
}
