<%@page import="java.util.List"%>
<%@page import="com.moshao.model.Product"%>
<%@page import="com.moshao.dao.ProductDao"%>
<%@page import="com.moshao.connection.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <title>E-Commerce Cart</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>

        <div class="container">
            <div class="card-header my-3">All Products</div>
            <div class="row">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Product Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                         // Get the real path to the SQL file
                        String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                            List<Product> products = productDao.getAllProducts();
                            for (Product product : products) {
                        %>
                        <tr>
                            <td><%= product.getProductId()%></td>
                            <td><%= product.getName()%></td>
                            <td>$<%= product.getPrice()%></td>
                            <td><%= product.getCategory()%></td>
                            <td><%= product.getSize() != null ? product.getSize() : ""%></td>
                            <td><%= product.getColor() != null ? product.getColor() : ""%></td>
                            <td>
                                <button type="button" class="btn btn-primary" onclick="showUpdateForm('<%= product.getProductId()%>', '<%= product.getName()%>', '<%= product.getPrice()%>', '<%= product.getCategory()%>', '<%= product.getSize()%>', '<%= product.getColor()%>')">Edit</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>

                </table>
            </div>
        </div>

        <%@include file="/includes/footer.jsp"%>

        <!-- Modal for Update Product Form -->
        <div id="updateForm" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Update Product</h4>
                    </div>
                    <div class="modal-body">
                        <form id="updateProductForm" action="updateProductServlet" method="post">
                            <input type="hidden" id="productId" name="productId">
                            <div class="form-group">
                                <label for="productName">Name:</label>
                                <input type="text" class="form-control" id="productName" name="productName">
                            </div>
                            <div class="form-group">
                                <label for="productPrice">Price:</label>
                                <input type="text" class="form-control" id="productPrice" name="productPrice">
                            </div>
                            <div class="form-group">
                                <label for="productCategory">Category:</label>
                                <input type="text" class="form-control" id="productCategory" name="productCategory">
                            </div>
                            <div class="form-group">
                                <label for="productSize">Size:</label>
                                <input type="text" class="form-control" id="productSize" name="productSize">
                            </div>
                            <div class="form-group">
                                <label for="productColor">Color:</label>
                                <input type="text" class="form-control" id="productColor" name="productColor">
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                    function showUpdateForm(productId, productName, productPrice, productCategory, productSize, productColor) {
                                        document.getElementById('productId').value = productId;
                                        document.getElementById('productName').value = productName;
                                        document.getElementById('productPrice').value = productPrice;
                                        document.getElementById('productCategory').value = productCategory;
                                        document.getElementById('productSize').value = productSize;
                                        document.getElementById('productColor').value = productColor;
                                        $('#updateForm').modal('show');
                                    }
        </script>
    </body>
</html>
