package listener;

import database.bean.Product;
import model.Items;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionListener implements HttpSessionListener {

	// -------------------------------------------------------
	// HttpSessionListener implementation
	// -------------------------------------------------------
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setAttribute(SessionKey.ITEMS, new Items());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	  /* Session is destroyed. */
	}

}
