package com.alevel;

import DBManaging.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class reg_servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/signup.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass1");

        Integer id;

        DBManager dbManager = new DBManager();
        RequestDispatcher requestDispatcher = null;

        if (!dbManager.isAlreadyExists("name", name) || !dbManager.isAlreadyExists("email", email)) {
            id = dbManager.addUserToDB(name, email, String.valueOf(pass.hashCode()));

            response.addCookie(new Cookie("user_id", String.valueOf(id)));


        }
        response.sendRedirect("/register");
    }
}



