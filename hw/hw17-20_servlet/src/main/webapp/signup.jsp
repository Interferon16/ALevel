<%--
  Created by IntelliJ IDEA.
  User: Interferon
  Date: 28.04.2018
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reg form</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1 align="center" ><b>REGISTRATION FORM</b></h1>
<form method="post" action="/register">
    <table style="margin: 0 auto">
        <tr>
            <td>Enter login</td>
            <td><input name="name" class="sign_input" type="text" id="inputName" placeholder="name"></td>
        </tr>
        <tr>
            <td>Enter email</td>
            <td><input name="email" class="sign_input" type="email" id="inputEmail" placeholder="email"></td>
        </tr>
        <tr>
            <td>Enter password</td>
            <td><input name="pass1" class="sign_input" type="password" id="inputPassword1" placeholder="pass"></td>
        </tr>
        <tr>
            <td>Repeat password</td>
            <td><input name="pass2" class="sign_input" type="password" id="inputPassword2" placeholder="pass"></td>
        </tr>
    </table>
    <br>
    <input class="btn-primary" type="button" value="Submit" onclick="validate(this.form)">
</form>

<script src="scripts/reg_valid.js"></script>
</body>
</html>
