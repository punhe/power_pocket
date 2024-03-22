package com.se4f7.SWP.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;
	private AuthService authService;

	public void init() {
		couponService = new CouponServiceImpl();
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		int Money = 0;
		List<CouponEntity> list = couponService.getWorkByName(search);
		int count = couponService.count();
		List<CouponEntity> list2 = couponService.getAllBill();
		for(CouponEntity coupon : list2){
			if(coupon.getStatus() == 0){
				Money += coupon.getAmount();
			}
			else{
				Money -= coupon.getAmount();
			}
		}

		int sumMoney = authService.getMoney(9);
		sumMoney = Money + sumMoney;

		request.setAttribute("sumMoney", sumMoney);
		request.setAttribute("money", sumMoney);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
