package com.Sigma.Sigma.domain.model.repository;

import com.Sigma.Sigma.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
