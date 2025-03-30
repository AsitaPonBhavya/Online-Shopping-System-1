package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.ecart.connection.DbCon;
import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);

            if (user != null) {
                request.getSession().setAttribute("auth", user);

                // Check if user is an admin or a customer
                String role = user.getRole(); // Assuming getRole() fetches the role from the User model

                // Generate OTP
                String otp = generateOTP();
                request.getSession().setAttribute("otp", otp);
                request.getSession().setAttribute("userRole", role); // Store the role in the session

                // Send OTP to email (or phone, depending on your use case)
                String userEmail = user.getEmail();
                sendOTPEmail(userEmail, otp);

                // Redirect to OTP verification page
                response.sendRedirect("verify-otp2.jsp");
            } else {
                out.println("Invalid login credentials.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate a 6-digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Send OTP to the user's email (using your email sending service)
    private void sendOTPEmail(String email, String otp) {
        // Logic to send email using JavaMail API or any third-party service like SendGrid
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp;
        // Implement the actual email sending code here
    }
}
