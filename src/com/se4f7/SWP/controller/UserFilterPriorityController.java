package com.se4f7.SWP.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class UserFilterPriorityController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;

	public void init() {
		couponService = new CouponServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String priorityString = request.getParameter("priority");
		int priority = Integer.parseInt(priorityString);
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
		if (priority == 2) {
			response.sendRedirect("./load-data-user");
		} else {
			try {
				List<CouponEntity> listOfUser1 = couponService.getPriorityUser(priority, userName);
				request.setAttribute("listP", listOfUser1);
				request.getRequestDispatcher("user-priority-filter.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
