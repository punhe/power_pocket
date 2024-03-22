<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@include file="./header.jsp" %>
    <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">

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

</head>

<body>
<br>
<br>
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

                        <form method="post" action="./register" id="form-registor">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Create new account</span>
                            </div>

                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg2}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg3}</h6>

                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-group">
                                        <label class="form-label" for="firstName">First name</label>
                                        <input type="text" id="firstName" class="form-control" name="firstName" rules="required" placeholder="First Name"/>
                                        <span class="form-message" style="color: red"></span>
                                    </div>

                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-group">
                                        <label class="form-label" for="lastName">Last name</label>
                                        <input type="text" id="lastName" class="form-control" name="lastName" rules="required" placeholder="Last Name"/>
                                        <span class="form-message" style="color: red"></span>
                                    </div>

                                </div>
                            </div>

                            <!-- Email input -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="username">Email</label>
                                <input id="username" class="form-control form-control-lg"
                                       placeholder="Enter a valid email" name="username" rules="required|min:6|email" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Password input -->
                            <div class="form-group mb-3">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" class="form-control form-control-lg pass"
                                       placeholder="Enter password" name="password" rules="required|min:13|upper|lower|number|special"/>
                                <span class="form-message" style="color: red"></span>
                                <p style="text-align: left; font-size: 0.7rem">Password must contains</p>
                                <ul class="requirement-list" style="list-style: none;">
                                    <li class="">
                                        <i class="fas fa-times-circle"></i>
                                        <span>Password has length from 13 to 50 characters.</span>
                                    </li>
                                    <li class="">
                                        <i class="fas fa-times-circle"></i>
                                        <span>At least 1 number (0...9)</span>
                                    </li>
                                    <li class="">
                                        <i class="fas fa-times-circle"></i>
                                        <span>At least 1 lowercase letter (a...z)</span>
                                    </li>
                                    <li class="">
                                        <i class="fas fa-times-circle"></i>
                                        <span>At least 1 uppercase letter (A...Z)</span>
                                    </li>
                                    <li class="">
                                        <i class="fas fa-times-circle"></i>
                                        <span>At least 1 special symbol (!...$)</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="g-recaptcha"
                                 data-sitekey="6Ld7NB0pAAAAADddbfQelSXMOLjWaqfHsBAVmpAV">
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Register</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Already an account ? <a href="login.jsp"
                                                                                                class="link-danger">Login</a></p>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <%@include file="footer.jsp" %>
</section>
<%@include file="./JS.jsp" %>
<script>
    Validator("#form-registor");
</script>
<script>
    window.addEventListener("DOMContentLoaded", (event) => {
        var passwordInput = document.getElementById("password");
        var requirements = [
            {regex: /.{13,50}/, index: 0}, // Minimum of 13 characters, Maximum of 50 characters
            {regex: /[0-9]/, index: 1}, // At least one number
            {regex: /[a-z]/, index: 2}, // At least one lowercase letter
            {regex: /[A-Z]/, index: 3}, // At least one uppercase letter
            {regex: /[^A-Za-z0-9]/, index: 4} // At least one special character
        ];
        var requirementList = document.querySelectorAll(".requirement-list li");

        passwordInput.addEventListener("keyup", (e) => {
            requirements.forEach(item => {
                var isValidPass = item.regex.test(e.target.value);
                console.log(e.target.value);
                var requirementItem = requirementList[item.index];

                // Updating class and icon of requirement item if requirement matched or not
                if (isValidPass) {
                    requirementItem.classList.add("valid");
                    requirementItem.firstElementChild.className = "fas fa-check-circle";
                } else {
                    requirementItem.classList.remove("valid");
                    requirementItem.firstElementChild.className = "fas fa-times-circle";
                }
            });
        });
    });
</script>
</body>

</html>