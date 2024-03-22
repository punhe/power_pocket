package com.se4f7.SWP.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.dto.response.UserResponseDto;
import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class LoadFilterDueController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;

	public void init() {
		couponService = new CouponServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String due = request.getParameter("due");
		int sumMoney= 0;
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}
		AuthService authService = new AuthServiceImpl();
		UserResponseDto u = authService.getUserInfo(userName);
		int userRole = u.getRole();
		List<CouponEntity> list = couponService.getAllTodoLimit(1);
		for(CouponEntity todo : list) {
			if(todo.getStatus() == 0){
				sumMoney += todo.getAmount();
			}
			else{
				sumMoney -= todo.getAmount();
			}
		}


		if (due.equalsIgnoreCase("all")) {
			if (userRole == 2) {
				response.sendRedirect("./load-data");
			} else {
				response.sendRedirect("./load-data-user");
			}
		} else {
			try {
				request.setAttribute("sumMoney", sumMoney);
				List<CouponEntity> listt = couponService.getWorkByDue(due);
				List<CouponEntity> listU = couponService.getWorkByDue(due, userName);
				request.setAttribute("list", listt);
				request.setAttribute("listU", listU);
				request.getRequestDispatcher("admin-filter.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
