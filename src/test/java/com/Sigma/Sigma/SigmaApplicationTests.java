package com.Sigma.Sigma;

import com.Sigma.Sigma.api.controller.AlunoController;
import com.Sigma.Sigma.domain.model.Aluno;
import com.Sigma.Sigma.domain.model.repository.AlunoRepository;
import com.Sigma.Sigma.service.CadastroAlunoService;
import io.restassured.http.ContentType;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SigmaApplicationTests {
}



