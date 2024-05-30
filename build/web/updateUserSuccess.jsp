<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
    <title>Update Success</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>
    <div class="container">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Update Successful!</h4>
            <p>The user has been updated successfully.</p>
            <hr>
            <p class="mb-0">Return to <a href="userUpdate.jsp">Update User Page</a>.</p>
        </div>
    </div>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>
