package com.se4f7.SWP.controller;

import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.service.AuthService;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.AuthServiceImpl;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadDeleteController", value = {"/LoadDeleteController"})
public class LoadDeleteController extends HttpServlet {
    private static final long serialVersionUID = 2860215007883522580L;

    private CouponService couponService;
    private AuthService authService;
    public void init() {
        couponService = new CouponServiceImpl();
        authService = new AuthServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            List<CouponEntity> list = couponService. getAllBillDeleteLimit(page);
            List<CouponEntity> list2 = couponService.getAllBillDelete();
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
            request.setAttribute("list", list);
            request.setAttribute("endP", endP);
            request.setAttribute("tag", page);
            request.setAttribute("count", count);
            request.getRequestDispatcher("IsDelete_manament.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}