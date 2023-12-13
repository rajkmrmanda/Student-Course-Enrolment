package com.stud.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, String>{

	Optional<Course> findTopByOrderByIdDesc();

	Optional<Course> findByCourseId(String courseId);

	Optional<Course> findByCourse(String course);

}
