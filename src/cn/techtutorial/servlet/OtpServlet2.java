package cn.techtutorial.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-otp2")
public class OtpServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the OTP from the form
        String otpEntered = request.getParameter("otp");

        // Get the OTP stored in the session (you should set this when sending the OTP)
        String otpGenerated = "050304"; // Replace with actual OTP generation logic

        // Get the user role from session
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("userRole"); // Assuming user role is stored in session

        // Check if OTP matches
        if (otpEntered.equals(otpGenerated)) {
            // OTP matches, check user role
            if ("admin".equals(userRole)) {
                // Redirect to admin dashboard
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                // Redirect to regular user home page
                response.sendRedirect("app.jsp");
            }
        } else {
            // OTP doesn't match, show an error
            response.getWriter().println("Invalid OTP. Please try again.");
        }
    }
}
