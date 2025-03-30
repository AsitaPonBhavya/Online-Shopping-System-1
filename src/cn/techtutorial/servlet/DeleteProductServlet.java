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

@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get the product ID from the request
        int productId = Integer.parseInt(request.getParameter("id"));

        // Delete product from the database
        try (Connection con = DbCon.getConnection()) {
            String query = "DELETE FROM products WHERE id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, productId);

                int rowCount = pst.executeUpdate();

                if (rowCount > 0) {
                    response.sendRedirect("admin-dashboard.jsp");  // Redirect to admin dashboard after successful deletion
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=DeletionFailed");  // Redirect back with error
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseError"); // Redirect on SQLException
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseConnectionFailed"); // Redirect on ClassNotFoundException
        }
    }
}
