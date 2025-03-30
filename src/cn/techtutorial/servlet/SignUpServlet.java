/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cn.ecart.connection.DbCon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt; // For password hashing

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        // Get form parameters
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(password);

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(hashedPassword);
        
        // Insert user data into the database
        try (Connection con = DbCon.getConnection()) {
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, password);  // Store the hashed password
                
                int rowCount = pst.executeUpdate();
                
                if (rowCount > 0) {
                    response.sendRedirect("login.jsp");  // Redirect to login page after successful sign-up
                } else {
                    response.sendRedirect("SignUp.jsp?error=SignupFailed");  // Redirect back to sign-up page on failure
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("SignUp.jsp?error=SignupFailed"); // Redirect on SQLException
        } catch (ClassNotFoundException e) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("SignUp.jsp?error=DatabaseConnectionFailed"); // Redirect on ClassNotFoundException
        }
    }
}
