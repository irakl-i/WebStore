package listener;

import database.bean.Product;
import model.Items;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent se) {
		// Create new Items data structure in user session.
		HttpSession session = se.getSession();
		session.setAttribute(SessionKey.ITEMS, new Items());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	  /* Session is destroyed. */
	}

}
