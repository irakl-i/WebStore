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
import java.util.Enumeration;


@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		Items items = (Items) session.getAttribute(SessionKey.ITEMS);
		ProductDAO dao = (ProductDAO) context.getAttribute(ContextKey.DAO);
		Product product = dao.getProductById(request.getParameter("id"));

		if (product != null) {
			// If the product isn't null, that means that "Add to Cart" was
			// called, so we just add the product to the items list.
			items.add(product);
		} else {
			// If the program is at this point, that means
			// that "Update Cart" button was pressed.
			Enumeration<String> enumeration = request.getParameterNames();

			while (enumeration.hasMoreElements()) {
				String id = enumeration.nextElement();

				// For every parameter, we get its corresponding product.
				product = dao.getProductById(id);

				// Parse the integer value from the form.
				int quantity = Integer.parseInt(request.getParameter(id));

				// Update the cart accordingly.
				items.update(product, quantity);
			}
		}

		// Forward user to the cart web-page.
		RequestDispatcher dispatcher = request.getRequestDispatcher("shopping-cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward user to the cart web-page.
		RequestDispatcher dispatcher = request.getRequestDispatcher("shopping-cart.jsp");
		dispatcher.forward(request, response);
	}

}
