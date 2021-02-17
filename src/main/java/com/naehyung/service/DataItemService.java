package com.naehyung.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naehyung.dao.ChatRoomDao;
import com.naehyung.dao.DataItemDao;
import com.naehyung.model.User;
import com.naehyung.web.model.DataItem;

@Service
@Transactional
public class DataItemService {

	@Autowired
	private DataItemDao dataItemDao;
	
	public void save(DataItem dataItem) {
		
		
		dataItemDao.save(dataItem);
	}
	
	
}
