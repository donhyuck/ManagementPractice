package com.ldh.java.mp.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/PrintDan2")
public class HomePrintDanJsp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HomePrintDanJsp 서블릿이 printDan.jsp에게 위임하여 요청처리
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response);
	}
}
