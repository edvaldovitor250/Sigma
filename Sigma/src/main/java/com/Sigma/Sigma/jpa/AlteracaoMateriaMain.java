package com.Sigma.Sigma.jpa;

import com.Sigma.Sigma.SigmaApplication;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlteracaoMateriaMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new
                SpringApplicationBuilder(SigmaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MateriaRepository alterar = applicationContext.getBean(MateriaRepository.class);

        Materia m1 = new Materia();
        m1.setId(1L);
        m1.setNome("Portugues");

        alterar.salvar(m1);

        System.out.println("Id da materia:" + m1.getId() + "\n" + "Nome da Materia:" + m1.getNome() );

    }


}
