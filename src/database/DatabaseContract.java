package database;

public final class DatabaseContract {
	private DatabaseContract() {
	}

	public static class ProductTable {
		public static final String TABLE_NAME = "products";
		public static final String COLUMN_NAME_ID = "productid";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_IMAGE = "imagefile";
		public static final String COLUMN_NAME_PRICE = "price";
	}

}

