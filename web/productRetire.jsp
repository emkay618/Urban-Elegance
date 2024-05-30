<%@ page import="com.moshao.model.Product" %>
<%@ page import="com.moshao.dao.ProductDao" %>
<%@ page import="com.moshao.connection.DbCon" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.moshao.model.User" %>

<%
     // Get the real path to the SQL file
    String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
    
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    ProductDao pd = new ProductDao(DbCon.getConnection(realPath));
    List<Product> products = pd.getAllProducts();
    ArrayList<Product> productsList = (ArrayList<Product>) session.getAttribute("productsList");
    if (productsList != null) {
        request.setAttribute("productsList", productsList);
    }

    // Generating base URL
    String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <title>E-Commerce Cart</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>

        <div class="container">
            <div class="card-header my-3">Retire Products</div>
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
                            if (!products.isEmpty()) {
                                for (Product p : products) {
                        %>
                        <tr>
                            <td><%= p.getProductId()%></td>
                            <td><%= p.getName()%></td>
                            <td>$<%= p.getPrice()%></td>
                            <td><%= p.getCategory()%></td>
                            <td><%= (p.getSize() != null) ? p.getSize() : ""%></td>
                            <td><%= (p.getColor() != null) ? p.getColor() : ""%></td>

                            <td>
                                <button type="button" class="btn btn-danger" onclick="retireProduct('<%= p.getProductId()%>')">Retire</button>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5">There is no product</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/includes/footer.jsp"%>
        <script>
            function retireProduct(productId) {
                $.ajax({
                    url: '/retire-product',
                    type: 'POST', 
                    data: {productId: productId}, // Pass productId as data
                    success: function (response) {
                        // Handle success response
                        console.log('Product retired successfully');
                    },
                    error: function (xhr, status, error) {
                        // Handle error response
                        console.error('Error retiring product:', error);
                    }
                });
            }

        </script>
    </body>
</html>
