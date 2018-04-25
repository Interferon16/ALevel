package com.alevel;

import DBManaging.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class reg_servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass1");
        Integer id;

        DBManager dbManager = new DBManager();
        RequestDispatcher requestDispatcher = null;

        if (!dbManager.isAlreadyExists("name", name) || !dbManager.isAlreadyExists("email", email)) {
            id = dbManager.addUserToDB(name, email, pass);

            response.addCookie(new Cookie("user_id", String.valueOf(id)));

            requestDispatcher = request.getRequestDispatcher("index.html");
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("signup.html");
            requestDispatcher.forward(request, response);
        }
    }
}

