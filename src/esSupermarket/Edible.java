package esSupermarket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Edible extends Product {

	private LocalDate expire_date;

	public Edible(String product_id, String product_description, float product_price, LocalDate exipire_date) {
		super(product_id, product_description, product_price);
		this.expire_date = exipire_date;
	}

	public LocalDate getExpire_date() {
		return expire_date;
	}

	@Override
	public String toString() {
		return "Edible - " + super.toString() + ", expiring date=[" + expire_date + "]";
	}

	@Override
	public void applyDiscount() {
		float discounted_price;
		long period = ChronoUnit.DAYS.between(LocalDate.now(), expire_date);
		if (period < 10)
			discounted_price = (float) (getProduct_price() - ((10 * getProduct_price()) / 100));
		else
			discounted_price = (float) (getProduct_price() - ((5 * getProduct_price()) / 100));
		this.setProduct_price(discounted_price);
	}
}
