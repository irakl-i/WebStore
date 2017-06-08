<%@ page import="database.bean.Product" %>
<%@ page import="listener.SessionKey" %>
<%@ page import="model.Items" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
            crossorigin="anonymous"></script>
    <title>Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>
<form action="Cart" method="post">
    <ul>
        <%
            // Used for formatting doubles.
            NumberFormat formatter = new DecimalFormat("#0.00");

            // Get items from session.
            Items items = (Items) session.getAttribute(SessionKey.ITEMS);

            double total = 0;
            for (Map.Entry entry : items) {
                // Get key and value from map entry.
                Product product = (Product) entry.getKey();
                int quantity = (int) entry.getValue();

                // Calculate the price.
                double price = product.getPrice() * quantity;

                // Calculate total price.
                total += price;
                out.println("<li> <input type=\"number\" value=\"" + quantity
                        + "\" name=\"" + product.getId() + "\">"
                        + " " + product.getName() + ", $" + formatter.format(price) + "</input><br>");
            }
            out.println("Total: $" + formatter.format(total));
            out.println("<button type=\"submit\" value=\"Update Cart\">Update Cart</button>");
        %>
    </ul>
    <a href="${pageContext.request.contextPath}/Products">Continue shopping</a>
</form>
</body>
</html>