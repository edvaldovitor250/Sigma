package com.Sigma.Sigma;

import com.Sigma.Sigma.api.controller.AlunoController;
import com.Sigma.Sigma.domain.model.Aluno;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.AlunoRepository;
import com.Sigma.Sigma.service.CadastroAlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlunoControllerTest {

    private AlunoController alunoController;
    private CadastroAlunoService cadastroAluno;
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void setup() {
        cadastroAluno = mock(CadastroAlunoService.class);
        alunoRepository = mock(AlunoRepository.class);
        alunoController = new AlunoController(cadastroAluno, alunoRepository);
    }

    @Test
    public void listarAlunosDeveRetornarListaDeAlunos() {
        List<Aluno> alunos = List.of(new Aluno(), new Aluno());
        when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> result = alunoController.listar();

        assertEquals(2, result.size());
    }

    @Test
    public void buscarAlunoExistenteDeveRetornarAluno() {
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        ResponseEntity<Aluno> response = alunoController.buscar(alunoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aluno, response.getBody());
    }

    @Test
    public void buscarAlunoInexistenteDeveRetornarNotFound() {
        Long alunoId = 1L;
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        ResponseEntity<Aluno> response = alunoController.buscar(alunoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void adicionarAlunoDeveRetornarAlunoCriado() {
        Aluno aluno = new Aluno();
        when(cadastroAluno.salvar(aluno)).thenReturn(aluno);

        ResponseEntity<?> response = alunoController.adicionar(aluno);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(aluno, response.getBody());
    }

    @Test
    public void adicionarAlunoComErroDeveRetornarBadRequest() {
        Aluno aluno = new Aluno();
        when(cadastroAluno.salvar(aluno)).thenThrow(EntidadeNaoEncontradaException.class);

        ResponseEntity<?> response = alunoController.adicionar(aluno);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
