package com.naehyung.web.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.naehyung.model.ChatRoom;

@Entity
@Table(name = "DataItem")
public class DataItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String content;
	private int viewType;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoom_id", nullable = false)
	@JsonBackReference
    private ChatRoom chatRoom;
	
	public DataItem() {
		
	}

    public DataItem(String content, String name ,int viewType) {
        this.content = content;
        this.viewType = viewType;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public int getViewType() {
        return viewType;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

}
