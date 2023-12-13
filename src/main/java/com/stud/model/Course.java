package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
public class Course {
	
	@Id
	String id;
	String courseId;
	int roomNo;
	String course;
	String description;

	public Course() {
		super();
	}

	public Course(String id, String courseId, int roomNo, String course, String description) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.roomNo = roomNo;
		this.course = course;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseId=" + courseId + ", roomNo=" + roomNo + ", course=" + course
				+ ", description=" + description + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
