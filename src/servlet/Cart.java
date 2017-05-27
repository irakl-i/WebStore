package servlet;

import database.bean.Product;
import database.dao.ProductDAO;
import listener.ContextKey;
import listener.SessionKey;
import model.Items;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		Items items = (Items) session.getAttribute(SessionKey.ITEMS);
		ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
		Product product = dao.getProductById(request.getParameter("id"));

		items.add(product);


		RequestDispatcher dispatcher = request.getRequestDispatcher("shopping-cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("shopping-cart.jsp");
		dispatcher.forward(request, response);
	}

}
