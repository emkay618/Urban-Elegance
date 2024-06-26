<%@page import="com.moshao.connection.DbCon"%>
<%@page import="com.moshao.dao.ProductDao"%>
<%@page import="com.moshao.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    //get cart list from arraylist
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;//initially
     // Get the real path to the SQL file
                String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
    if (cart_list != null) {
        ProductDao pDao = new ProductDao(DbCon.getConnection(realPath));
        cartProduct = pDao.getCartProducts(cart_list);//received all cart list products
        double total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("total", total);//add to request scope
        request.setAttribute("cart_list", cart_list);//add to request scope
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <title>UrbanElegance</title>
        <style type="text/css">

            .table tbody td{
                vertical-align: middle;
            }
            .btn-incre, .btn-decre{
                box-shadow: none;
                font-size: 25px;
            }
        </style>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>

        <div class="container my-3">
            <div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>
                        <th scope="col">Buy Now</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (cart_list != null) {
                            for (Cart c : cartProduct) {
                    %>
                    <tr>
                        <td><%=c.getName()%></td>
                        <td><%=c.getCategory()%></td>
                        <td><%= dcf.format(c.getPrice())%></td>
                        <td>
                            <form action="order-now" method="post" class="form-inline">
                                <input type="hidden" name="id" value="<%= c.getProductId()%>" class="form-input">
                                <div class="form-group d-flex justify-content-between w-50">
                                    <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getProductId()%>"><i class="fas fa-minus-square"></i></a>
                                    <input type="text" name="quantity" class="form-control w-50"  value="<%=c.getQuantity()%>" readonly> 
                                    <a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getProductId()%>"><i class="fas fa-plus-square"></i></a> 
                                </div>
                                <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                            </form>
                        </td>
                        <td><a href="remove-from-cart?id=<%=c.getProductId()%>" class="btn btn-sm btn-danger">Remove</a></td>
                    </tr>

                    <%
                            }
                        }%>
                </tbody>
            </table>
        </div>

        <%@include file="/includes/footer.jsp"%>
    </body>
</html>