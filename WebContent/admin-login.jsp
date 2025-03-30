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
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/includes/head.jsp"%>
	<title>Admin Login</title>

	<!-- Google reCAPTCHA API -->
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Admin Login</div>
			<div class="card-body">
				<form action="admin-login" method="post">
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

				<!-- Removed Sign Up Option -->

			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>
