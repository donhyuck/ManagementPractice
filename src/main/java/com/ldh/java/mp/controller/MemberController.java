package com.ldh.java.mp.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void actionJoin() {

	}

}