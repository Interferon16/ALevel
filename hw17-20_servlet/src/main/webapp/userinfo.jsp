<%@ page import="DBManaging.DBManager" %>
<%@ page import="entitys.User" %><%--
  Created by IntelliJ IDEA.
  User: Interferon
  Date: 27.04.2018
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
<button onclick="location.href='/index.html'">Back</button>
<%
    DBManager<User> dbManager = new DBManager();
    User user;
    RequestDispatcher requestDispatcher = null;

    Cookie[] cookie = request.getCookies();

    String user_id = "";
    for (Cookie c : cookie) {
        if (c.getName().equals("user_id")) {
            if (dbManager.isAlreadyExists("id", c.getValue())) {
                user_id = c.getValue();
            }
        }
    }

    user = dbManager.getUserInfo(Integer.valueOf(user_id));
%>

<table align="center" style="font-weight:bold;font-size:40px">
    <tr>
        <td><%
            out.println("User id: " + user.getId());
        %></td>
    </tr>
    <tr>
        <td><%
            out.println("\n User name: " + user.getName());
        %></td>
    </tr>
    <tr>
        <td><%
            out.println("\n User email: " + user.getEmail());
        %></td>
    </tr>
    <tr>
        <td><%
            out.println("\n User passhash: " + user.getPass());
        %></td>
    </tr>
</table>
</body>
</html>
