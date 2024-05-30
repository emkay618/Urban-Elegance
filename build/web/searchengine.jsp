<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.Product" %>
<%@ page import="com.moshao.dao.ProductDao" %>
<%@ page import="com.moshao.connection.DbCon" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/92d70a2fd8.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/searchengine.css">
        <style>
            .nav-link {
                text-decoration: none; /* Remove underline */
                color: #000000;
                padding: 8px 15px;
            }
            .nav-item {
                list-style: none; /* Remove dot */
                display: inline; /* Display as inline */
                margin-right: 10px;
                margin-left: 10px;
            }
            .nav-link:hover {
                border-left: 4px solid orangered;
                border-radius: 4px;
                cursor: pointer;
                padding-left: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="sidebar">
                <div class="sidehead">
                    <div class="dots">
                        <i class="fa-solid fa-circle"></i>
                        <i class="fa-solid fa-circle" style="color: #333;"></i>
                        <i class="fa-solid fa-circle"></i>
                    </div>
                    <hr style="margin: 15px 0; border: 1px solid #eee;">
                </div>
                <div class="sidebody" style="height: 69vh;">
                    <div class="searchbar">
                        <input placeholder="Search..." id="searchBar" name="searchBar" type="text">
                        <i class="fa-solid fa-magnifying-glass glass"></i>
                    </div>
                </div>
                <div class="sidefoot">
                    <hr style="margin: 15px 0; border: 1px solid #eee;">
                    <div class="social-icons">
                        <i class="fa-brands fa-square-facebook"></i>
                        <i class="fa-brands fa-youtube"></i>
                        <i class="fa-brands fa-square-github"></i>
                    </div>
                </div>
            </div>
            <div class="data">
                <div class="top">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                </div>
                <div class="header">
                    <p>BUY EVERYTHING ONLINE</p>
                </div>
                <div class="body">
                    <div id="root">
                        <!-- Products will be displayed here -->
                        <%
                            try {
                                // Get the real path to the SQL file
                                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
                                ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
                                List<Product> products = productDao.getAllProductsWithImageURLs();
                                for (Product product : products) {
                        %>
                        <div class='box'>
                            <div class='img-box'>
                                <img class='images' src='<%= product.getImageUrl()%>'></img>
                            </div> 
                            <div class='bottom'>
                                <p><%= product.getName()%></p>
                                <h2>$ <%= product.getPrice()%>.00</h2>
                                <button>View</button>
                            </div>
                        </div>
                        <%
                                }
                            } catch (Exception e) {
                                out.println("Error fetching and displaying products: " + e.getMessage());
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <script src="javascript/searchengine.js"></script>
    </body>
</html>
