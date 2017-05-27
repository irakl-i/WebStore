<%@ page import="listener.SessionKey" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Items" %>
<%@ page import="database.bean.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>
<form action="Cart" method="post">
    <%
        out.println("<ul>");
        Items items =  (Items) session.getAttribute(SessionKey.ITEMS);
        for (Map.Entry entry : items) {
            Product product = (Product) entry.getKey();
            int quantity = (int) entry.getValue();
            double price = product.getPrice() * quantity;
            out.println("<li> <input type=\"text\" value=\"" + quantity
                    + "\" name=\"" + product.getId() + "\">"
                    + " " + product.getName() + ", $" + price + "</input>");
        }
        out.println("</ul>");
        out.println("Total: $");
        out.println("<button type=\"submit\" value=\"Update Cart\">Update Cart</button>");
    %>
</form>
</body>
</html>
