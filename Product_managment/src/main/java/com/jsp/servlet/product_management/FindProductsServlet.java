package com.jsp.servlet.product_management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findProduct")
public class FindProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Replace with your database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/product_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Sumit@7978";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create SQL select statement
            String sql = "SELECT productId, productName, price FROM product_db";
            stmt = conn.prepareStatement(sql);

            // Execute query
            rs = stmt.executeQuery();

            // Process the result set
            List<Map<String, Object>> products = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> product = new HashMap<>();
                product.put("productId", rs.getInt("productId"));
                product.put("productName", rs.getString("productName"));
                product.put("price", rs.getInt("price"));
                products.add(product);
            }

            // Debug print
            System.out.println("Products found: " + products.size());

            // Set the products list as a request attribute
            request.setAttribute("products", products);

            // Forward to displayProduct.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayProduct.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            // Clean up JDBC resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
