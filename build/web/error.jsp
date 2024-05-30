<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #721c24;
            margin-top: 0;
        }
        p {
            color: #721c24;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
        <p>An error occurred while processing your request. Please try again later.</p>
        <a href="index.jsp">Go Back Home</a>
    </div>
</body>
</html>
