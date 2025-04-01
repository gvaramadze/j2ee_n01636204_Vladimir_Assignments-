package com.example.ejbtutorial;

import com.example.ejb.CalculatorBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @EJB
    private CalculatorBean calculatorBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int a = 10;
        int b = 5;

        int sum = calculatorBean.add(a, b);
        int difference = calculatorBean.subtract(a, b);
        int product = calculatorBean.multiply(a, b);
        int quotient = calculatorBean.divide(a, b);

        response.setContentType("text/plain");
        response.getWriter().println(a + " + " + b + " = " + sum);
        response.getWriter().println(a + " - " + b + " = " + difference);
        response.getWriter().println(a + " * " + b + " = " + product);
        response.getWriter().println(a + " / " + b + " = " + quotient);
    }
}
