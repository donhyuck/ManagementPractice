package com.ldh.java.mp.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.service.HomeService;

public class HomeController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private HomeService homeService;

	public HomeController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;

		homeService = new HomeService(conn);
	}
}
