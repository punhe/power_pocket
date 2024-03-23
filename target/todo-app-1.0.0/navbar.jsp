<%@page import="com.se4f7.SWP.service.AuthService"%>
<%@page import="com.se4f7.SWP.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.SWP.dto.response.UserResponseDto"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top text-black">
    <div class="container-fluid justify-content-between">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <%
        String user = (String) session.getAttribute("user");
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                }
            }
        } else {
            response.sendRedirect("error.jsp");
        }

    %>

    <div class="collapse navbar-collapse text-black justify-content-between d-inline" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                &nbsp;&nbsp;&nbsp;
                <a href="index.jsp" style="color: black"><i class="fa fa-home" style="margin-top: 5px; font-size: 20px" aria-hidden="true"></i></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp"> Home</a>
            </li>
            <li class="nav-item active mb-2"> <a class="nav-link mb-2" href="#contact">Contact</a>
            </li>
            <%                if (user != null) {
                AuthService authService = new AuthServiceImpl();
                UserResponseDto u = authService.getUserInfo(user);
                int userRole = u.getRole();
            %>
            <li class="nav-item">
                <a class="nav-link"
                        <%
                            if (userRole == 2) {

                        %>
                   href="./load-data?role=<%= u.getRole() %>"
                        <%  } else {%>
                   href="./load-data-user?role=<%= u.getRole() %>">
                    <% } %>
                    <h6>Manager</h6>

            </li>

            <% } %>
        </ul>
        <div class="form-inline">
            <% if (user != null) {%>
            <a href="profile.jsp" style="text-decoration: none; color: #000; margin-top: 10px"><p>Welcome, <%= user%></p></a>
            &nbsp;
            <a href="./logout" style="text-decoration: none; color: #000">
                Logout
            </a>
            <% } else {%>
            <button class="btn-outline-success my-2 my-sm-0 bg-primary"style="border-radius: 5px" onclick="handleToLogin()">Login</button>
            &nbsp;&nbsp;&nbsp;
            <button class="btn-outline-success my-2 my-sm-0 bg-primary"style="border-radius: 5px" onclick="handleToRegister()">Register</button>
            &nbsp;&nbsp;&nbsp;
            <% }%>
        </div>
    </div>
    </>
</nav>
