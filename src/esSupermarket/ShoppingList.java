package esSupermarket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ShoppingList {

	// 10 edibles in the first 10 slots and 10 non edibles in the remaining ones
	private String[] products_typo = { "Prosciutto Cotto Of Parma 80gr", "Kelloggs Cereals 300gr",
			"Low-fat yogurt 300cl", "Fresh salmon 100gr", "Fresh Parmalat Milk 250ml", "Greek Feta Cheese 160gr",
			"Vigentinian Pesto 200gr", "Wholemeal Bread 400gr", "Freezed Chips 500gr", "White Magnum Ice Cream 390gr",
			"Set of plastic bottles n.100", "Oral B Toothbrush", "Paper Made Box", "Set Of Forks", "Black Jeans",
			"Dixan Bleach 1.6kg", "Marlboro Gold", "White T-Shirt Unisex XL", "Phone Cover", "Degreaser For Dishes" };

	private String[] materials_typo = { "plastic", "glass", "paper", "metal", "cloth", "chemicals", "alcohol" };

	Scanner sc = new Scanner(System.in);
	private ArrayList<Product> list_of_products;
	private ArrayList<Product> shopping_list;
	String product_choice;

	public ShoppingList() {
		list_of_products = new ArrayList<Product>();
		shopping_list = new ArrayList<Product>();

		for (int i = 0; i < products_typo.length / 2; i++) {
			list_of_products.add(new Edible(generateID(), products_typo[i],
					(float)(Math.random() * 19 + 1), generateExpireDate()));
		}

		for (int i = products_typo.length / 2; i < products_typo.length; i++) {
			list_of_products.add(new NonEdible(generateID(), products_typo[i],
					(float)(Math.random() * 19 + 1), generateMaterial()));
		}
	}

	private String generateID() {
		return Integer.toHexString(ThreadLocalRandom.current().nextInt());
	}

	private String generateMaterial() {
		return materials_typo[ThreadLocalRandom.current().nextInt(0, materials_typo.length)];
	}

	private LocalDate generateExpireDate() {
		LocalDate today_date = LocalDate.now();
		LocalDate result = LocalDate.of(today_date.getYear(), today_date.getMonthValue(),
				ThreadLocalRandom.current().nextInt(today_date.getDayOfMonth(), 31));
		return result;
	}

	public void showAllProducts() {
		for (Product p : list_of_products) {
			System.out.println(p.toString());
		}
	}

	public void showEdibleProducts() {
		for (Product p : list_of_products) {
			if (p instanceof Edible)
			System.out.println(p.toString());
		}
	}

	public void showNonEdibleProducts() {
		for (Product p : list_of_products) {
			if (p instanceof NonEdible)
			System.out.println(p.toString());
		}
	}

	public void doShopping() {
		if (shopping_list.isEmpty()) {
			int product_index;
			do {
				showAllProducts();
				System.out.print(
						"\nChoose a product by writing its name (ex. prosciutto, cheese)" + "\nLeave blank to stop: ");
				product_choice = sc.nextLine();
				product_index = productExists(product_choice);
				if (product_index != -1 && !product_choice.isBlank()) {
					System.out.println(
							"Add " + list_of_products.get(product_index).getProduct_description() + " ? (y/n)");
					if (sc.nextLine().equalsIgnoreCase("y")) {
						shopping_list.add(list_of_products.get(product_index));
						System.out.println("Product added!");
					}
				}
				else if (product_index == -1 && !product_choice.isBlank())
					System.out.println("The given product doesn't exist!\n");
			} while(!product_choice.isBlank());
		} else {
			System.out.println("You can only create 1 shopping list!");
		}
	}

	private int productExists(String product_choice) {
		for (int j = 0; j < list_of_products.size(); j++) {
			boolean isPresent = list_of_products.get(j).getProduct_description().toLowerCase()
					.indexOf(product_choice.toLowerCase()) != -1 ? true : false;
			if (isPresent)
				return j;
		}
		return -1;
	}

	public void checkOut() {
		float counter = 0;
		if (!shopping_list.isEmpty()) {
				for (Product p : shopping_list) {
					p.applyDiscount();
				}
				System.out.println("Discount applied!\n");
				System.out.println("Here's the updated shopping list:");
				showShoppingList();
				
			for (int j = 0; j < shopping_list.size(); j++) {
				counter += shopping_list.get(j).getProduct_price();
			}
			System.out.printf("Total is: € %.2f", counter);
	}
		else
			System.out.println("You don't have a shopping list yet!");
	}
	
	private void showShoppingList() {
		for (Product p : shopping_list) {
			System.out.println(p.toString());
		}
	}
}
