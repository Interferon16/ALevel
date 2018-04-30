package com.alevel;

import DBManaging.DBManager;
import entitys.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/userinfo")
public class user_info extends HttpServlet {
    private DBManager<User> dbManager = new DBManager();
    private RequestDispatcher requestDispatcher = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookie = request.getCookies();

        boolean trueUser = false;

        String user_id="";
        for (Cookie c : cookie) {
            if (c.getName().equals("user_id")) {
                if (dbManager.isAlreadyExists("id", c.getValue())) {
                    trueUser=true;
                    user_id=c.getValue();
                }
            }
        }
        if(trueUser){
            createUserInfo(user_id,response);
        }else{
            requestDispatcher = request.getRequestDispatcher("signup.html");
            requestDispatcher.forward(request,response);
        }

    }

    private void createUserInfo(String id,HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        User user = dbManager.getUserInfo(Integer.valueOf(id));
        writer.println("" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">\n" +
                 "<title>User Info</title>\n" +
                 "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css\"\n" +
                 "integrity=\"sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4\" crossorigin=\"anonymous\">"+
                "</head>" +
                "<body>" +
                "<a class=\"btn btn-outline-primary\" href=\"index.html\">Back</a><br>"+
                "User id: " + user.getId() + "<br>" +
                "User name: " + user.getName() + "<br>" +
                "User email: " + user.getEmail() + "<br>" +
                "User passhash: " + user.getPass() + "<br>" +
                "</body>" +
                "</html>");
        writer.flush();
        writer.close();

    }
}
