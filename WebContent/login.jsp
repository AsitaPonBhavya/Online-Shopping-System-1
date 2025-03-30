<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		if ("admin".equals(auth.getRole())) {
			response.sendRedirect("admin-dashboard.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/includes/head.jsp"%>
	<title>Omnigo</title>

	<!-- Google reCAPTCHA API -->
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email address</label> 
						<input type="email" name="login-email" class="form-control" placeholder="Enter email" required>
					</div>
					<div class="form-group">
						<label>Password</label> 
						<input type="password" name="login-password" class="form-control" placeholder="Password" required>
					</div>

					<!-- Google reCAPTCHA widget -->
					<div class="g-recaptcha" data-sitekey="6LcRyWIqAAAAAHEuQim-TLlE-w1THqLmpUS6DLUw"></div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>

				<!-- Admin Login Button -->
				<div class="text-center mt-3">
					<button class="btn btn-secondary" onclick="window.location.href='admin-login.jsp'">Admin Login</button>
				</div>

				<div class="text-center mt-3">
					<p>Don't have an account? <a href="SignUp.jsp">Sign up</a></p>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>
