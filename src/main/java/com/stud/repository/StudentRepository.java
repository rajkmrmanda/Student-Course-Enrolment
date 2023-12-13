package com.stud.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String>{

	Optional<Student> findTopByOrderByIdDesc();

	Optional<Student> findByEmailAndPassword(String email, String password);

	Optional<Student> findByStudId(String string);


}
