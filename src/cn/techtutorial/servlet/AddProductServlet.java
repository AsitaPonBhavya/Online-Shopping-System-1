package cn.techtutorial.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import cn.ecart.connection.DbCon;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/add-product")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get form parameters
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        int stock = Integer.parseInt(request.getParameter("stock"));
        Part imagePart = request.getPart("image");

        // Specify the path to save the uploaded image
        String imagePath = "product-image/" + imagePart.getSubmittedFileName();
        String savePath = getServletContext().getRealPath("") + File.separator + imagePath;

        // Save the uploaded image to the specified path
        imagePart.write(savePath);

        // Insert product data into the database
        try (Connection con = DbCon.getConnection()) {
            String query = "INSERT INTO products (name, price, category, stock, image) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, name);
                pst.setDouble(2, price);
                pst.setString(3, category);
                pst.setInt(4, stock);
                pst.setString(5, imagePart.getSubmittedFileName()); // Store the image file name

                int rowCount = pst.executeUpdate();

                if (rowCount > 0) {
                    response.sendRedirect("admin-dashboard.jsp");  // Redirect to admin dashboard after successful addition
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=AdditionFailed");  // Redirect back with error
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseError"); // Redirect on SQLException
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin-dashboard.jsp?error=DatabaseConnectionFailed"); // Redirect on ClassNotFoundException
        }
    }
}
