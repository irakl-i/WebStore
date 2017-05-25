package listener; /**
 * Created by Luka on 25/5/17.
 */

import database.DatabaseInfo;
import database.dao.ProductDAO;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class ContextListener implements ServletContextListener{
	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	public void contextInitialized(ServletContextEvent sce) {
	  /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

		ServletContext context = sce.getServletContext();
		try {
			PoolProperties properties = new PoolProperties();
			properties.setDriverClassName("com.mysql.jdbc.Driver");
			properties.setUrl(DatabaseInfo.MYSQL_DATABASE_SERVER);
			properties.setUsername(DatabaseInfo.MYSQL_USERNAME);
			properties.setPassword(DatabaseInfo.MYSQL_PASSWORD);
			properties.setInitialSize(10);
			properties.setMaxActive(100);

			DataSource pool = new DataSource();
			pool.setPoolProperties(properties);
			context.setAttribute(ContextKey.CONNECTION_POOL, pool);

			ProductDAO dao = new ProductDAO(pool);
			context.setAttribute(ContextKey.DAO, dao);
		} catch (Exception ignored) {
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
	}

}
