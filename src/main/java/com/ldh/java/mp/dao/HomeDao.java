package com.ldh.java.mp.dao;

import java.sql.Connection;

public class HomeDao {

	private Connection conn;

	public HomeDao(Connection conn) {
		this.conn = conn;
	}
}
