package esSupermarket;

public class NonEdible extends Product {

	private String material;

	public NonEdible(String product_id, String product_description, float product_price, String material) {
		super(product_id, product_description, product_price);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	@Override
	public String toString() {
		return "Non Edible - " + super.toString() + ", material=[" + material + "]";
	}

	@Override
	public void applyDiscount() {
		float discounted_price;
		if (material.equalsIgnoreCase("carta") || material.equalsIgnoreCase("vetro")
				|| material.equalsIgnoreCase("plastica"))
			discounted_price = (float) (getProduct_price() - ((20 * getProduct_price()) / 100));
		else
			discounted_price = (float) (getProduct_price() - (5 * getProduct_price()) / 100);
		this.setProduct_price(discounted_price);
	}
}