<%@page import="com.moshao.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <%@include file="/includes/head.jsp"%>
        <title>UrbanElegance</title>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">Add Product</div>
                <div class="card-body">
                    <form action="add-product" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="name">Product Name:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="category">Category:</label>
                            <input type="text" class="form-control" id="category" name="category" required>
                        </div>
                        <div class="form-group">
                            <label for="price">Price:</label>
                            <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" required>
                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantity:</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" min="0" required>
                        </div>
                        <div class="form-group">
                            <label for="size">Size:</label>
                            <input type="text" class="form-control" id="size" name="size">
                        </div>
                        <div class="form-group">
                            <label for="color">Color:</label>
                            <input type="text" class="form-control" id="color" name="color">
                        </div>
                        <div class="form-group">
                            <label for="image">Image:</label>
                            <input type="file" class="form-control-file" id="image" name="image" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Product</button>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
