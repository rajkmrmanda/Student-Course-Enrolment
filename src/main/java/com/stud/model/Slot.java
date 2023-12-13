package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "slot")
public class Slot {
	
	@Id
	String id;
	String slotId;
	String profId;
	String firstName;
	String day;
	String time;

	public Slot() {
		super();
	}

	public Slot(String id, String slotId, String profId, String firstName, String day, String time) {
		super();
		this.id = id;
		this.slotId = slotId;
		this.profId = profId;
		this.firstName = firstName;
		this.day = day;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", slotId=" + slotId + ", profId=" + profId + ", firstName=" + firstName + ", day="
				+ day + ", time=" + time + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
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


}
