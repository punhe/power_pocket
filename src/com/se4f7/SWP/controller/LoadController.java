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

public class LoadController extends HttpServlet {

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
		String index = request.getParameter("page");
		int Money= 0;
		if (index == null) {
			index = "1";
		}
		int page = Integer.parseInt(index);
		try {
			int count = couponService.count();
			int endP = count / 5;
			if (count % 5 != 0) {
				endP++;
			}
			List<CouponEntity> list = couponService.getAllTodoLimit(page);
			List<CouponEntity> list2 = couponService.getAllBill();
			request.setAttribute("list", list);
			for(CouponEntity coupon : list2){
				if(coupon.getStatus() == 0){
					Money += coupon.getAmount();
				}
				else{
					Money -= coupon.getAmount();
				}
			}


			int sumMoney = authService.getMoney(4);
			sumMoney = Money + sumMoney;

			request.setAttribute("sumMoney", sumMoney);

			request.setAttribute("endP", endP);
			request.setAttribute("tag", page);
			request.setAttribute("count", count);
			request.getRequestDispatcher("admin.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
