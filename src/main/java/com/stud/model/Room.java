package com.stud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room")
public class Room {
	
	@Id
	String id;
	String roomId;
	int roomNo;
	int capacity;
	String status;

	public Room() {
		super();
	}

	public Room(String id, String roomId, int roomNo, int capacity, String status) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.capacity = capacity;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomId=" + roomId + ", roomNo=" + roomNo + ", capacity=" + capacity + ", status="
				+ status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
