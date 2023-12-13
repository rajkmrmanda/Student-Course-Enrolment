package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "schedule")
public class Schedule {
	
	@Id
	String id;
	String scheId;
	String scheDate;
	String slotId;
	String day;
	String time;
	String profId;
	String firstName;
	String courseId;
	String course;
	int roomNo;
	int avlSeats;

	public Schedule() {
		super();
	}

	public Schedule(String id, String scheId, String scheDate, String slotId, String day, String time, String courseId,
			String course, String profId, String firstName, int roomNo, int avlSeats) {
		super();
		this.id = id;
		this.scheId = scheId;
		this.scheDate = scheDate;
		this.slotId = slotId;
		this.day = day;
		this.time = time;
		this.courseId = courseId;
		this.course = course;
		this.profId = profId;
		this.firstName = firstName;
		this.roomNo = roomNo;
		this.avlSeats = avlSeats;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", scheId=" + scheId + ", scheDate=" + scheDate + ", slotId=" + slotId + ", day="
				+ day + ", time=" + time + ", courseId=" + courseId + ", course=" + course + ", profId=" + profId
				+ ", firstName=" + firstName + ", roomNo=" + roomNo + ", avlSeats=" + avlSeats + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScheId() {
		return scheId;
	}

	public void setScheId(String scheId) {
		this.scheId = scheId;
	}

	public String getScheDate() {
		return scheDate;
	}

	public void setScheDate(String scheDate) {
		this.scheDate = scheDate;
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getProfId() {
		return profId;
	}

	public void setProfId(String profId) {
		this.profId = profId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getAvlSeats() {
		return avlSeats;
	}

	public void setAvlSeats(int avlSeats) {
		this.avlSeats = avlSeats;
	}

	
}
