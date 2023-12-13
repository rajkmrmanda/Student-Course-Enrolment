package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enroll")
public class Enroll {
	
	@Id
	String id;
	String enrollId;
	String scheId;
	String studId;
	String firstName;
	int roomNo;
	String date;
	String time;
	String course;
	String status;
	
	public Enroll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enroll(String id, String enrollId, String scheId, String studId, String firstName, int roomNo, String date,
			String time, String course, String status) {
		super();
		this.id = id;
		this.enrollId = enrollId;
		this.scheId = scheId;
		this.studId = studId;
		this.firstName = firstName;
		this.roomNo = roomNo;
		this.date = date;
		this.time = time;
		this.course = course;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Enroll [id=" + id + ", enrollId=" + enrollId + ", scheId=" + scheId + ", studId=" + studId
				+ ", firstName=" + firstName + ", roomNo=" + roomNo + ", date=" + date + ", time=" + time + ", course="
				+ course + ", status=" + status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}

	public String getScheId() {
		return scheId;
	}

	public void setScheId(String scheId) {
		this.scheId = scheId;
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
