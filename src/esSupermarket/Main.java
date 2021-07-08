package esSupermarket;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ShoppingList obj = new ShoppingList();

		int menu_choice = 0;
		do {
			try {
				System.out.println("\n0. End the program" + "\n1. Show all the products and their price"
						+ "\n2. Show edible products" + "\n3. Show non edible products" + "\n4. Create a shopping list"
						+ "\n5. Check out and end the program");
				menu_choice = sc.nextInt();
				sc.nextLine();

				switch (menu_choice) {
				case 0:
					System.out.println("Program terminated!");
					System.exit(0);
					break;
				case 1:
					obj.showAllProducts();
					break;
				case 2:
					obj.showEdibleProducts();
					break;
				case 3:
					obj.showNonEdibleProducts();
					break;
				case 4:
					obj.doShopping();
					break;
				case 5:
					obj.checkOut();
					System.exit(0);
				default:
					System.out.println("Wrong number. Try again.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect choice input! Ending the program.");
			}
		} while (menu_choice != 0);
		sc.close();
	}

}
