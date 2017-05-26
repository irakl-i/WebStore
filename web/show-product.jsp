<%@ page import="database.bean.Product" %>
<%@ page import="database.dao.ProductDAO" %>
<%@ page import="listener.ContextKey" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Luka
  Date: 26/5/17
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ServletContext context = request.getServletContext();
        ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);

        List<Product> products = dao.getProducts(request.getParameter("id"));
        Product product = products.get(0);
    %>

    <title><%= product.getName() %>
    </title>
</head>
<body>
<h1><%= product.getName() %>
</h1>
<img src="<%="/store-images/" + product.getImage() %>" alt="<%= product.getName() %>">

<form action="Cart" method="post">
    $<%= product.getPrice() %> <input name="id" type="hidden" value="<%= product.getId() %>"/>
    <input type="submit" value="Add to Cart"/>
</form>

</body>
</html>
