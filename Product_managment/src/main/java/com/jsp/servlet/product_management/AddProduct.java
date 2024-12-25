package com.jsp.servlet.product_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Replace with your database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/product_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Sumit@7978";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form parameters
       int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create SQL insert statement
            String sql = "INSERT INTO product_db (productId, productName, price) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Set parameters
            stmt.setInt(1, productId);
            stmt.setString(2, productName);
            stmt.setInt(3, price);
            

            // Execute update
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h2>Product added successfully!</h2>");
            } else {
                out.println("<h2>Failed to add product.</h2>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            // Clean up JDBC resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("<a href='addProduct.jsp'>Back to Add Product</a>");
        out.close();
    }
}