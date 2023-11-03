package com.Sigma.Sigma;
import com.Sigma.Sigma.api.controller.MateriaController;
import com.Sigma.Sigma.domain.model.Materia;
import com.Sigma.Sigma.domain.model.exception.EntidadeEmUsoException;
import com.Sigma.Sigma.domain.model.exception.EntidadeNaoEncontradaException;
import com.Sigma.Sigma.domain.model.repository.MateriaRepository;
import com.Sigma.Sigma.service.CadastroMateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MateriaControllerTest {

    @InjectMocks
    private MateriaController materiaController;

    @Mock
    private CadastroMateriaService cadastroMateria;

    @Mock
    private MateriaRepository materiaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarMateriasDeveRetornarListaDeMaterias() {
        List<Materia> materias = materiaController.listar();
        assertEquals(2, materias.size());
    }

    @Test
    public void buscarMateriaExistenteDeveRetornarMateria() {
        Long materiaId = 1L;
        Materia materia = new Materia();
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));

        ResponseEntity<Materia> response = materiaController.buscar(materiaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materia, response.getBody());
    }

    @Test
    public void buscarMateriaInexistenteDeveRetornarNotFound() {
        Long materiaId = 1L;
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.empty());

        ResponseEntity<Materia> response = materiaController.buscar(materiaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void adicionarMateriaDeveRetornarMateriaCriada() {
        Materia materia = new Materia();
        when(cadastroMateria.salvar(materia)).thenReturn(materia);

        ResponseEntity<?> response = materiaController.adicionar(materia);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(materia, response.getBody());
    }

    @Test
    public void adicionarMateriaComErroDeveRetornarBadRequest() {
        Materia materia = new Materia();
        when(cadastroMateria.salvar(materia)).thenThrow(EntidadeNaoEncontradaException.class);

        ResponseEntity<?> response = materiaController.adicionar(materia);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void atualizarMateriaExistenteDeveRetornarMateriaAtualizada() {
        Long materiaId = 1L;
        Materia materia = new Materia();
        Materia materiaAtual = new Materia();
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.of(materia));
        when(cadastroMateria.salvar(materiaAtual)).thenReturn(materiaAtual);

        ResponseEntity<?> response = materiaController.atualizar(materiaId, materiaAtual);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materiaAtual, response.getBody());
    }

    @Test
    public void atualizarMateriaInexistenteDeveRetornarNotFound() {
        Long materiaId = 1L;
        Materia materiaAtual = new Materia();
        when(materiaRepository.findById(materiaId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = materiaController.atualizar(materiaId, materiaAtual);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void removerMateriaExistenteDeveRetornarNoContent() {
        Long materiaId = 1L;
        doNothing().when(cadastroMateria).excluir(materiaId);

        ResponseEntity<Materia> response = materiaController.remover(materiaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void removerMateriaInexistenteDeveRetornarNotFound() {
        Long materiaId = 1L;
        doThrow(EntidadeNaoEncontradaException.class).when(cadastroMateria).excluir(materiaId);

        ResponseEntity<Materia> response = materiaController.remover(materiaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void removerMateriaEmUsoDeveRetornarConflict() {
        Long materiaId = 1L;
        doThrow(EntidadeEmUsoException.class).when(cadastroMateria).excluir(materiaId);

        ResponseEntity<Materia> response = materiaController.remover(materiaId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
