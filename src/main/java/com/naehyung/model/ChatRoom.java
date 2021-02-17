package com.naehyung.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.naehyung.web.model.DataItem;

@Entity
@Table(name = "ChatRoom")
public class ChatRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String roomId;
	
	@ElementCollection
	private List<String> userList = new ArrayList<>();
	@JsonManagedReference
	@OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<DataItem> dataItemList = new ArrayList<>();;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public List<DataItem> getDataItemList() {
		return dataItemList;
	}
	public void setDataItemList(List<DataItem> dataItemList) {
		this.dataItemList = dataItemList;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
	

}
