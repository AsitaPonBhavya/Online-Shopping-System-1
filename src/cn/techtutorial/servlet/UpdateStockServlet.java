package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.ecart.connection.DbCon;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.dao.ProductDao;
import cn.techtutorial.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/update-stock")
public class UpdateStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			User authUser = (User) session.getAttribute("auth");

			// Check if the user is an admin
			if (authUser != null) {
				int productId = Integer.parseInt(request.getParameter("id"));
				int newStock = Integer.parseInt(request.getParameter("stock"));
                                System.out.println("i hate u " + productId);


				ProductDao productDao = new ProductDao(DbCon.getConnection());
                                
				boolean updateSuccess = productDao.updateProductStock(productId, newStock);

				if (updateSuccess) {
					out.println("<h3 style='color:green; text-align: center'>Stock updated successfully for product ID: " + productId + "</h3>");
					response.sendRedirect("admin-dashboard.jsp");
				} else {
					out.println("<h3 style='color:red; text-align: center'>Failed to update stock for product ID: " + productId + "</h3>");
					response.sendRedirect("admin-dashboard.jsp");
				}
			} else {
				// If the user is not an admin, restrict access
				out.println("<h3 style='color:red; text-align: center'>Unauthorized access. Admins only.</h3>");
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateStockServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
