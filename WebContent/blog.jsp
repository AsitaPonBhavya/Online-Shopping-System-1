<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Blog</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background: #007BFF;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
        }
        h1 {
            margin: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .blog-post {
            margin-bottom: 20px;
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .blog-post:last-child {
            border-bottom: none;
        }
        .post-title {
            font-size: 24px;
            margin: 0;
        }
        .post-date {
            color: #999;
            font-size: 14px;
            margin: 5px 0;
        }
        .post-content {
            font-size: 16px;
            line-height: 1.6;
        }
        footer {
            text-align: center;
            padding: 20px;
            background: #007BFF;
            color: #fff;
            position: relative;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<header>
    <h1>My Blog</h1>
</header>

<div class="container">
    <div class="blog-post">
        <h2 class="post-title"><a href="blog1.jsp" style="color: #007BFF; text-decoration: none;">Memories</a></h2>
        <p class="post-date">October 17, 2024</p>
        <p class="post-content">This is a short snippet of the first blog post. It contains interesting insights about a topic and invites the reader to explore more.</p>
    </div>

    <div class="blog-post">
        <h2 class="post-title"><a href="blog2.jsp" style="color: #007BFF; text-decoration: none;">Anokha 2024</a></h2>
        <p class="post-date">October 17, 2024</p>
        <p class="post-content">This is a short snippet of the second blog post. It provides some thought-provoking information that encourages further reading.</p>
    </div>

    <div class="blog-post">
        <h2 class="post-title"><a href="blog3.jsp" style="color: #007BFF; text-decoration: none;">Friendship</a></h2>
        <p class="post-date">October 12, 2024</p>
        <p class="post-content">This is a short snippet of the third blog post. Here, we discuss various aspects of an interesting subject matter.</p>
    </div>
</div>

<footer>
    <p>&copy; 2024 My Blog. All rights reserved.</p>
</footer>

</body>
</html>