package com.se4f7.SWP.controller;

import com.se4f7.SWP.entities.Graph;
import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DailyAmountServlet", value = {"/DailyAmountServlet"})
public class DailyAmountServlet extends HttpServlet {
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

        try {
            List<Graph> dailyAmounts = authService.getListMoney();
            request.setAttribute("dailyAmounts", dailyAmounts);
            request.getRequestDispatcher("admin-graph.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}