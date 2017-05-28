package model;

import database.bean.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Items extends HashMap implements Iterable<Map.Entry> {
	private Map<Product, Integer> map;

	public Items() {
		map = new HashMap<>();
	}

	/**
	 * Checks if the product exists and adds it to the map
	 * or increments its value.
	 * @param product
	 */
	public void add(Product product) {
		if (!map.containsKey(product)) {
			map.put(product, 1);
		} else {
			map.put(product, map.get(product) + 1);
		}
	}

	/**
	 * Updates the value of the product. Removes it
	 * from the map if quantity is less than 0.
	 * @param product
	 * @param quantity
	 */
	public void update(Product product, int quantity) {
		if (quantity <= 0) {
			map.remove(product);
		} else {
			map.put(product, quantity);
		}
	}

	/**
	 * Returns the size of the map.
	 * @return items size
	 */
	public int size() {
		return map.size();
	}


	@Override
	public Iterator iterator() {
		return map.entrySet().iterator();
	}
}
