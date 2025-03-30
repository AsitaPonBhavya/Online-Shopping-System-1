
<%@page import="cn.techtutorial.model.User"%>
<%@page import="cn.ecart.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.Product"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth == null || !"admin".equals(auth.getRole())) {
        // If not logged in or not an admin, redirect to login page
        response.sendRedirect("login.jsp");
        return;
    }

    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();

    // Check for error messages
    String errorMessage = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <%@include file="/includes/head.jsp"%>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>

    <div class="container">
        <div class="card-header my-3">Manage Products</div>
        
        <%-- Display Error Message if Present --%>
        <% if (errorMessage != null) { %>
            <div class="alert alert-danger">
                <%= errorMessage %>
            </div>
        <% } %>
        
        <div class="row">
            <%
            if (!products.isEmpty()) {
                for (Product p : products) {
            %>
            <div class="col-md-3 my-3">
                <div class="card w-100">
                    <img class="card-img-top" src="product-image/<%= p.getImage() %>" alt="Product Image">
                    <div class="card-body">
                        <h5 class="card-title"><%= p.getName() %></h5>
                        <h6 class="price">Price: Rs.<%= p.getPrice() %></h6>
                        <h6 class="category">Category: <%= p.getCategory() %></h6>
                        <h6 class="stock">Stock: <%= p.getStock() %></h6>
                        
                        <!-- Edit Stock Form -->
                        <form action="edit-product" method="post" class="mt-3">
                            <div class="form-group">
                                <label for="stock">Update Stock:</label>
                                <input type="number" name="stock" value="<%= p.getStock() %>" class="form-control" required>
                                <input type="hidden" name="product-id" value="<%= p.getId() %>">
                                <input type="hidden" name="name" value="<%= p.getName() %>"> <!-- Hidden input for product name -->
                                <input type="hidden" name="price" value="<%= p.getPrice()%>"> <!-- Hidden input for product price -->
                                <input type="hidden" name="category" value="<%= p.getCategory()%>"> <!-- Hidden input for product category -->

                            </div>
                            <button type="submit" class="btn btn-warning">Update Stock</button>
                        </form>

                        <!-- Delete Product -->
                        <a href="delete-product?id=<%= p.getId() %>" class="btn btn-danger mt-3">Delete Product</a>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
                out.println("<div class='col-12'><h4>No products available.</h4></div>");
            }
            %>
        </div>
        
        
        
        
        

        <!-- Add New Product Section -->
        <div class="mt-5">
            <h3>Add New Product</h3>
            <form action="add-product" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Product Name:</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" name="price" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="category">Category:</label>
                    <input type="text" name="category" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="stock">Stock:</label>
                    <input type="number" name="stock" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="image">Product Image:</label>
                    <input type="file" name="image" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success">Add Product</button>
            </form>
        </div>
    </div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>
