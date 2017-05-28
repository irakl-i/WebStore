package database.bean;

public class Product {
	private String id;
	private String name;
	private String image;
	private double price;

	/*
	Every method is self-explanatory. No need for pointless documentation that adds clutter to the code.
	 */

	public Product() {
	}

	public Product(String id, String name, String image, double price) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", image='" + image + '\'' +
				", price=" + price +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
		if (!getId().equals(product.getId())) return false;
		if (!getName().equals(product.getName())) return false;
		return getImage().equals(product.getImage());
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getImage().hashCode();
		temp = Double.doubleToLongBits(getPrice());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
