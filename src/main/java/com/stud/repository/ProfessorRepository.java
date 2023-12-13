package com.stud.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String>{

	Optional<Professor> findTopByOrderByIdDesc();

	Optional<Professor> findByEmailAndPassword(String email, String password);

	Optional<Professor> findByProfId(String string);


}
