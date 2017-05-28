<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
    <title>Mafia JD2</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="css/mainstyle.css" rel="stylesheet">
</head>
<body>

<div class="wrapper">
    <header class="header">
        <img src="images/mainlogo.png"/>
        <p class="topquote">"The quiter you become, the more you are able to hear."</p>
        <img class="bloodhand" src="images/bloodhand.png"/>
    </header><!-- .header-->
    <hr/>
    <nav class="navigation">
        <ul>
            <li><a href="#">Dummy</a></li>
            <li><a href="#" onclick="return false;">Dummy</a></li>
            <li><a href="#" onclick="return false;">Dummy</a></li>
            <li><a href="#" onclick="return false;">About</a></li>
            <li><a href="#" onclick="return false;">Log In</a></li>
        </ul>
    </nav><!-- .navigation -->
    <hr/>
    <br/><br/>
    <main class="content">
        <strong>Retrieved location from database:</strong>
        <br/>
        <h1>${requestScope.location.name}</h1>
        <br/>
        <strong>Coords:</strong>
        <h2>${requestScope.location.coords}</h2>
    </main><!-- .content -->
</div><!-- .wrapper -->

<br/>
<footer class="footer">
    <hr/>
    <strong>Mafia:</strong> ItAcademy final JD2 project. Copyright &#169; Yury Vaschenko 2017
</footer><!-- .footer -->
</body>
</html>
