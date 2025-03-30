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

@WebServlet("/edit-product")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    // Get form parameters
        String productIdStr = request.getParameter("product-id");
        String name = request.getParameter("name");
        
        double priceStr = Double.parseDouble(request.getParameter("price"));
        System.out.println("i love " + name);

        String category = request.getParameter("category");
        String stockStr = request.getParameter("stock");
        
        

        // Check for null parameters
        if (productIdStr == null || stockStr == null) {
            
            response.sendRedirect("admin-dashboard.jsp?error=MissingParameters");
            return;
        }
        System.out.println("Product ID:0987 " + stockStr);

        // Parse parameters
        int productId = Integer.parseInt(productIdStr);
        System.out.println("Product ID:123 " + priceStr);
        double price = priceStr;
        System.out.println("Product ID:456 " + price);
        int stock = Integer.parseInt(stockStr);
        System.out.println("Product ID:789 " + stock);

        
        

        // Update product data in the database
        try (Connection con = DbCon.getConnection()) {
            String query = "UPDATE products SET stock = ? WHERE id = ?";
            System.out.println("Product ID: ghfkgf" + stockStr);
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, stock);
                pst.setInt(2, productId);
                
                

                int rowCount = pst.executeUpdate();

                if (rowCount > 0) {
                    String na = request.getParameter("name");
                    System.out.println("Product ID: nee oru loose" + na);
                    response.sendRedirect("admin-dashboard.jsp");  // Redirect to admin dashboard after successful update
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=UpdateFailed");  // Redirect back with error
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseError"); // Redirect on SQLException
        } catch (ClassNotFoundException e) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseConnectionFailed"); // Redirect on ClassNotFoundException
        }
    }

}
