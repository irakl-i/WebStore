package listener;

import database.DatabaseInfo;
import database.dao.ProductDAO;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
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

			context.setAttribute(ContextKey.DAO, new ProductDAO(pool));
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
