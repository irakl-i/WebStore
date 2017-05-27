<%@ page import="database.bean.Product" %>
<%@ page import="database.dao.ProductDAO" %>
<%@ page import="listener.ContextKey" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ServletContext context = request.getServletContext();
    ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
    Product product = dao.getProductById(request.getParameter("id"));
%>
<html>
<head>
    <title><%= product.getName() %>
    </title>
</head>
<body>
<h1><%= product.getName() %>
</h1>
<img src="<%="/store-images/" + product.getImage() %>" alt="<%= product.getName() %>">

<%--<form action="Cart" method="post">--%>
<%--$<%= product.getPrice() %> <input name="id" type="hidden" value="<%= product.getId() %>"/>--%>
<%--<input type="submit" value="Add to Cart"/>--%>
<%--</form>--%>

</body>
</html>
