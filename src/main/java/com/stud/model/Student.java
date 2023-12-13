package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
	
	@Id
	String id;
	String studId;
	String name;
	String phone;
	String email;
	String gender;
	String status;
	String password;

	public Student() {
		super();
	}

	public Student(String id, String studId, String name, String phone, String email, String gender, String status,
			String password) {
		super();
		this.id = id;
		this.studId = studId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.status = status;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studId=" + studId + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", gender=" + gender + ", status=" + status + ", password=" + password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
