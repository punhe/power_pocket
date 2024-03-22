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

public class UserDataController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;

	public void init() {
		couponService = new CouponServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("page");
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
		if (index == null) {
			index = "1";
		}
		int page = Integer.parseInt(index);
		try {
			int count = couponService.countByUser(userName);
			int endP = count / 5;
			if (count % 5 != 0) {
				endP++;
			}
			List<CouponEntity> list = couponService.getAllWorkUser(page, userName);
			request.setAttribute("listU", list);
			request.setAttribute("endP", endP);
			request.setAttribute("tag", page);
			request.setAttribute("count", count);
			request.getRequestDispatcher("user.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
