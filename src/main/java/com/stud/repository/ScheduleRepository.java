package com.stud.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Enroll;
import com.stud.model.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, String>{

	Optional<Schedule> findTopByOrderByIdDesc();

	Optional<List<Schedule>> findAllByProfId(String profId);
	Optional<List<Schedule>> findAllByCourse(String course);

	Optional<List<Schedule>> findAllByScheIdNotIn(List<String> enrollStr);

	Optional<Schedule> findByScheId(String scheId);

}
