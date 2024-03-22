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

public class LoadFilterController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;

	public void init() {
		couponService = new CouponServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String statusString = request.getParameter("status");
		int status = Integer.parseInt(statusString);
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
		if (status == 4) {
			if (userRole == 2) {
				response.sendRedirect("./load-data");
			} else {
				response.sendRedirect("./load-data-user");
			}
		} else {
			try {
				List<CouponEntity> list = couponService.getFilter(status);
				List<CouponEntity> listOfUser = couponService.getFilterUser(status, userName);
				request.setAttribute("list", list);
				request.setAttribute("listU", listOfUser);
				request.getRequestDispatcher("admin-filter.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
