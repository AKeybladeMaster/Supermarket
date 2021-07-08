package esSupermarket;

public abstract class Product {

	private String product_id;
	private String product_description;
	private float product_price;
	
	public Product(String product_id, String product_description, float product_price) {
		this.product_id = product_id;
		this.product_description = product_description;
		this.product_price = product_price;
	}
	
	public abstract void applyDiscount();
	
	public String getProduct_id() {
		return product_id;
	}
	
	public String getProduct_description() {
		return product_description;
	}
	
	public double getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	@Override
	public String toString() {
		return "product id=[" + product_id + "], description=[" + product_description + "], price=[" + product_price + "]";
	}
}
