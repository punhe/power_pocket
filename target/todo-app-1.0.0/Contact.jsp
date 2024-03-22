<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 19/03/2024
  Time: 9:10 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div>
                <h2>Contact Admin</h2>
                <form action="./contact">
                    <div class="form-group">
                        <label for="fullName">Full Name:</label>
                        <input placeholder="Input your name" type="text" id="fullName" name="fullName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input placeholder="Input your email" type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="message">Message:</label>
                        <textarea placeholder="Input your message" id="message" name="message" rows="4" class="form-control" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-outline-success">Send Message</button>
                </form>
            </div>
        </div>
        <div class="col-md-6"> <div class="intro-section"> <h2>Introducing Receipt/Payment App</h2>  </div> </div>
    </div>
</div>
<%@include file="./JS.jsp" %>
<%@include file="./footer.jsp" %>
</body>
</html>
