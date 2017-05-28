package database.dao;

import database.DBContract;
import database.DBInfo;
import database.bean.Product;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private DataSource pool;

	public ProductDAO(DataSource dataSource) {
		this.pool = dataSource;
	}

	/**
	 * Gets list of products in the database.
	 * @return list of products.
	 */
	public List<Product> getProducts() {
		// Create a new empty list.
		List<Product> products = new ArrayList<>();

		Connection connection = null;
		try {
			// Get the connection from the pool.
			connection = pool.getConnection();


			Statement statement = connection.createStatement();
			statement.executeQuery("USE " + DBInfo.MYSQL_DATABASE_NAME);

			// Prepare and execute 'SELECT' query.
			String query = "SELECT * FROM " + DBContract.ProductTable.TABLE_NAME + ";";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iterate over result set and add products to the list.
			while (resultSet.next()) {
				products.add(fetchProduct(resultSet));
			}

			// Close statement and result set.
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) try {
				// Returns the connection to the pool somehow.
				// (https://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html)
				connection.close();
			} catch (Exception ignored) {
			}
		}

		return products;
	}

	/**
	 * Gets products with given id.
	 * @param id
	 * @return product with given id
	 */
	public Product getProductById(String id) {
		Product product = null;

		Connection connection = null;
		try {
			// Get the connection from the pool.
			connection = pool.getConnection();

			// Execute 'USE database' query.
			Statement statement = connection.createStatement();
			statement.executeQuery("USE " + DBInfo.MYSQL_DATABASE_NAME);

			// Prepare and execute 'SELECT' query.
			String query = "SELECT * FROM " + DBContract.ProductTable.TABLE_NAME + " WHERE productid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id); // Set the "?" parameter as id.
			ResultSet resultSet = preparedStatement.executeQuery();

			// Get the product from result set.
			resultSet.next();
			product = fetchProduct(resultSet);

			// Close statement and result set.
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) try {
				// Returns the connection to the pool somehow.
				// (https://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html)
				connection.close();
			} catch (Exception ignored) {
			}
		}

		return product;
	}

	/**
	 * Creates and returns product from result set.
	 * @param resultSet
	 * @return product
	 * @throws SQLException
	 */
	private Product fetchProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();

		// Set values.
		product.setId(resultSet.getString(DBContract.ProductTable.COLUMN_NAME_ID));
		product.setName(resultSet.getString(DBContract.ProductTable.COLUMN_NAME_NAME));
		product.setImage(resultSet.getString(DBContract.ProductTable.COLUMN_NAME_IMAGE));
		product.setPrice(resultSet.getDouble(DBContract.ProductTable.COLUMN_NAME_PRICE));

		return product;
	}
}
