package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html><head><style>"
                + "body { font-family: Arial, sans-serif; background-color: #f4f4f9; text-align: center; padding: 50px; }"
                + "h1 { color: #333; }"
                + "p { font-size: 20px; color: #666; }"
                + "</style></head><body>"
                + "<h1>Saurab's sample java application!</h1>"
                + "<p>Welcome to my stylish web application.</p>"
		+ "<img src='https://media1.tenor.com/m/sczscPhkz4MAAAAd/waiting-bored.gif' alt='Borr' /> <br> <br> "
		+ "<img src='https://media1.tenor.com/m/UO9ZhAR6J9sAAAAd/gif.gif' alt='DeadPool' /> "
                + "</body></html>");
    }
}

