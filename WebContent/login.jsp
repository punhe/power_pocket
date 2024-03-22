
<!--Page Directive: xác định ngôn ngữ của trang (Java), kiểu nội dung (text/html), và bảng mã trang (UTF-8).-->
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!--Chỉ thị này nhập thư viện thẻ core của JSTL (JavaServer Pages Standard Tag Library), cho phép sử dụng các thẻ JSTL trong trang JSP.-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">

<head>
    <%@include file="./header.jsp" %>
    <title>Login</title>
</head>
<style>
    body {
        background-color: hsl(218, 41%, 15%);
    }

    .background-radial-gradient {
        background-image: radial-gradient(650px circle at 0% 0%, hsl(218, 41%, 35%) 15%, hsl(218, 41%, 30%) 35%, hsl(218, 41%, 20%) 75%, hsl(218, 41%, 19%) 80%, transparent 100%), radial-gradient(1250px circle at 100% 100%, hsl(218, 41%, 45%) 15%, hsl(218, 41%, 30%) 35%, hsl(218, 41%, 20%) 75%, hsl(218, 41%, 19%) 80%, transparent 100%);
        overflow: hidden;
        position: relative;
    }

    .background-radial-gradient::before {
        content: '';
        height: 220px;
        width: 220px;
        top: -60px;
        left: -130px;
        background: radial-gradient(#44006b, #ad1fff);
        overflow: hidden;
        position: absolute;
    }

    .background-radial-gradient::after {
        content: '';
        border-radius: 38% 62% 63% 37% / 70% 33% 67% 30%;
        bottom: -60px;
        right: -110px;
        width: 300px;
        height: 300px;
        background: radial-gradient(#44006b, #ad1fff);
        overflow: hidden;
        position: absolute;
    }

    .bg-glass {
        background-color: hsla(0, 0%, 100%, 0.9) !important;
        backdrop-filter: saturate(200%) blur(25px);
    }
</style>

<body>

<!--Thẻ JSTL này đặt một biến có tên là "cookie" với giá trị là các cookie từ yêu cầu hiện tại.-->
<c:set var="cookie" value="${pageContext.request.cookies}"/>

<section class="background-radial-gradient overflow-hidden">
    <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
        <div class="row gx-lg-5 align-items-center mb-5">
            <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10">
                <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
                    The best system <br />
                    <span style="color: hsl(218, 81%, 75%)">for your management</span>
                </h1>
                <p class="mb-4 opacity-70" style="color: hsl(218, 81%, 85%)">
                    We have planned to develop a revenue and expenditure management module with the primary objective of streamlining the process and minimising the time required for financial management.
                </p>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
                <div class="card bg-glass">
                    <div class="card-body px-4 py-5 px-md-5">
                        <!--Link tới Servlet doGet LoginController sau đó qua form login.jsp rồi tiến hành về lại doPost của Servlet-->
                        <form action="./login" method="post" id="form-login">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Login with your account</span>
                            </div>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: blue; text-align: left;">${msg1}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: blue; text-align: left;">${msg2}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg}</h6>

                            <!-- Nhập username -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="form3Example3">Email</label>
                                <input id="username" class="form-control form-control-lg"
                                       placeholder="Enter email" name="username" rules="required|email"  value="${cookie.u.value}"/>
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Nhập password -->
                            <div class="form-group mb-3">
                                <label class="form-label" for="form3Example4">Password</label>
                                <input type="password" id="password" class="form-control form-control-lg"
                                       placeholder="Enter password" name="password" rules="required" value="${cookie.p.value}" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Checkbox nhớ mật khẩu -->
                                <div class="form-check mb-0">
                                    <input class="form-check-input me-2" type="checkbox" ${cookie.r.value != null ? 'checked' : ''} name="remember" value="ON" id="form2Example3" />
                                    <label class="form-check-label" for="form2Example3">
                                        Remember me
                                    </label>
                                </div>
                                <!--Link tới jsp quên mật khẩu-->
                                <a href="./forget" class="text-body">Forgot password?</a>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>

                                <!--Link tới JSP đăng ký hoặc trở về trang home-->
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="register.jsp"
                                                                                                  class="link-danger">Register</a></p>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Home page?<a href="index.jsp"
                                                                                     class="link-danger"> Go back</a></p>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="./footer.jsp" %>
</section>
<%@include file="./JS.jsp" %>
<script>
    //            Check biểu mẫu login
    Validator("#form-login");
</script>
</body>

</html>