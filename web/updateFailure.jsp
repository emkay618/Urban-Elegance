<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp"%>
        <title>Update Failure</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <div class="container">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Update Failed!</h4>
                <p>Failed to update the product. Please try again.</p>
                <hr>
                <p class="mb-0">Return to <a href="productUpdate.jsp">Update Product Page</a>.</p>
            </div>
        </div>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
