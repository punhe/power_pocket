package com.se4f7.SWP.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CommentEntity;
import com.se4f7.SWP.service.CommentService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.CommentServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class ViewCMTController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private CouponService couponService;
	private CommentService commentService;

	public void init() {
		couponService = new CouponServiceImpl();
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("taskId"));
		String createdByUser = couponService.getCreatedById(id);

		List<CommentEntity> comments = commentService.getComments(id, createdByUser);
		request.setAttribute("comment", comments);
		request.getRequestDispatcher("view-cmt.jsp").forward(request, response);
	}

}
