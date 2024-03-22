package com.se4f7.SWP.controller;

import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoadAll", value = {"/LoadAll"})
public class LoadAll extends HttpServlet {
    private CouponService couponService;
    private AuthService authService;
    public void init() {
        couponService = new CouponServiceImpl();
        authService = new AuthServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int role1 = authService.getTotalU1();
        int role2 = authService.getTotalU2();
        int roleOther = authService.getTotalUOther();

        int receipt = authService.getTotalReceipt();
        int payment = authService.getTotalPayment();
        int delete = couponService.getTotalDeleteBill();
        int userRole = (int) authService.getUserRole(userName);
        if(userRole == 1){
            response.sendRedirect("login.jsp");
        }else{
            request.setAttribute("role1", role1);
            request.setAttribute("role2", role2);
            request.setAttribute("roleOther", roleOther);
            request.setAttribute("receipt", receipt);
            request.setAttribute("payment", payment);
            request.setAttribute("delete", delete);
            request.getRequestDispatcher("totalRole.jsp").forward(request, response);
        }
    }
}