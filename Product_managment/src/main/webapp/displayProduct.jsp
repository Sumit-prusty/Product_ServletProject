<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 800px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <body>
    <div class="container">
        <h2>Display Products</h2>

        <!-- Form for filtering or searching products -->
        <form action="findProduct" method="GET">
            <label for="search">Search Products:</label>
            <input type="text" id="search" name="search" placeholder="Enter product name">
            <button type="submit">Search</button>
        </form>

        <table>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Price</th>
            </tr>
            <% 
                List<Map<String, Object>> products = (List<Map<String, Object>>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (Map<String, Object> product : products) {
            %>
            <tr>
                <td><%= product.get("productId") %></td>
                <td><%= product.get("productName") %></td>
                <td><%= product.get("price") %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No products found.</td>
            </tr>
            <% 
                }
            %>
        </table>
    </div>
</body>

</html>
