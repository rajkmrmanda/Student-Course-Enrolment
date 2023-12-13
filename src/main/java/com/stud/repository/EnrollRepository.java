package com.stud.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Enroll;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, String>{

	Optional<Enroll> findTopByOrderByIdDesc();

	Optional<List<Enroll>> findAllByStudId(String string);

	Optional<List<Enroll>> findAllByDate(String date);

	Optional<List<Enroll>> findAllByDateAndStatus(String date, String string);

	Optional<List<Enroll>> findAllByFirstName(String string);

	Optional<List<Enroll>> findAllByStudIdAndDate(String string, String scheDate);



}
