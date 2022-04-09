package com.ldh.java.mp.service;

import java.sql.Connection;

import com.ldh.java.mp.dao.HomeDao;

public class HomeService {

	private Connection conn;
	private HomeDao homeDao;

	public HomeService(Connection conn) {
		this.conn = conn;
		this.homeDao = new HomeDao(conn);
	}
}
