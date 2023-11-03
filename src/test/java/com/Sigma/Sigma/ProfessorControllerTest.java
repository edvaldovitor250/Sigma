package com.Sigma.Sigma;
import com.Sigma.Sigma.domain.model.Professor;
import com.Sigma.Sigma.domain.model.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProfessorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    public void testListarProfessores() {
        // Preparação de dados (adicionar professores ao repositório, se necessário)

        // Realize uma solicitação HTTP para listar professores
        Professor[] professores = restTemplate.getForObject("http://localhost:" + port + "/professor", Professor[].class);

        // Verifique se a lista de professores não é nula e contém pelo menos um professor
        assertThat(professores).isNotNull();
        assertThat(professores.length).isGreaterThan(0);
    }

    @Test
    public void testCriarProfessor() {
        Professor novoProfessor = new Professor();
        novoProfessor.setNome("Nome do Novo Professor");

        Professor professorCriado = restTemplate.postForObject("http://localhost:" + port + "/professor/criar", novoProfessor, Professor.class);

        assertThat(professorCriado).isNotNull();
        assertThat(professorCriado.getId()).isNotNull();

        professorRepository.deleteById(professorCriado.getId());
    }

}
