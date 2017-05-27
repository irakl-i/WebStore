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

	public void add(Product product) {
		if (!map.containsKey(product)) {
			map.put(product, 1);
		} else {
			map.put(product, map.get(product) + 1);
		}
	}

	public void update(Product product, int quantity) {
		if (quantity <= 0) {
			map.remove(product);
		} else {
			map.put(product, quantity);
		}
	}

	public int get(Product product) {
		return map.get(product);
	}

	public int size() {
		return map.size();
	}

	public boolean contains(Product product) {
		return map.containsKey(product);
	}

	@Override
	public Iterator iterator() {
		return map.entrySet().iterator();
	}
}
