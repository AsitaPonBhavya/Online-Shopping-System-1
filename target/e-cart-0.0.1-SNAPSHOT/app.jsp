<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Select an Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .app-button {
            display: inline-block;
            margin: 20px;
            padding: 15px 30px;
            font-size: 18px;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .app-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <h1>Welcome! Please choose an application:</h1>
    
    <a href="index.jsp" class="app-button">eCart</a>
    <a href="blog.jsp" class="app-button">Blog</a>

</body>
</html>