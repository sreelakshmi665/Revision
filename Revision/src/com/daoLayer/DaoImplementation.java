package com.daoLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.dbConnection.Connect;
import com.entity.CustomerEntity;
import com.entity.CustomerShoppingEntity;
import com.entity.ItemEntity;

public class DaoImplementation {
	Connection con;
	static Scanner scan = new Scanner(System.in);
	Connect connect = new Connect();

	public Set<CustomerEntity> Register(Set<CustomerEntity> set) throws Exception {
		int customerId;
		String customerName, contactNumber;
		System.out.println("Enter id:");
		customerId = integerValidate();
		System.out.println("Enter name:");
		customerName = scan.next();
		System.out.println("Enter contact number");
		contactNumber = scan.next();
		if (!validMobileNumber(contactNumber)) {
			do {
				System.out.println("Enter the Valid Mobile number Note(Don't add+91)");
				contactNumber = scan.next();
			} while (!validMobileNumber(contactNumber));
		}
		CustomerEntity emp = new CustomerEntity(customerId, customerName, contactNumber);
		set.add(emp);
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String sree = "insert into customers values(" + customerId + "," + "'" + customerName + "'" + "," + "'"
				+ contactNumber + "')";
		st.executeUpdate(sree);
		connect.closeResource(st);
		connect.closeResource(con);
		return set;
	}

	private static boolean validMobileNumber(String mobileNumber) {
		if (mobileNumber.length() != 10) {
			return false;
		}
		if (!(mobileNumber.charAt(0) >= '5' && mobileNumber.charAt(0) <= '9')) {
			return false;
		}
		for (int index = 1; index < 10; index++) {
			if (!(mobileNumber.charAt(index) >= '0' && mobileNumber.charAt(index) <= '9')) {
				return false;
			}
		}
		return true;
	}

	public static int integerValidate() {
		int data = 0;
		boolean validate = false;
		while (validate == false) {
			if (scan.hasNextInt()) {
				data = scan.nextInt();
				validate = true;
			} else if (!scan.hasNextInt()) {
				System.out.println("Please Enter a Number in Integer!");
				scan.next();
			}
		}
		return data;
	}

	public Set<CustomerShoppingEntity> shoppingDetails(Set<CustomerEntity> set, Set<ItemEntity> item) throws Exception {
		// TODO Auto-generated method stub
		Set<CustomerShoppingEntity> shop = new HashSet<CustomerShoppingEntity>();
		CustomerEntity cust = null;
		System.out.println("Enter customer id:");
		int custId = scan.nextInt();
		double price = 0.0;
		boolean check = true;
		for (CustomerEntity val : set) {
			if (val.getCustomerId() == custId) {
				check = false;
				cust = val;
				displayItems(item);
			}
		}
		if (!check) {
			System.out.println("Enter no of items to be purchased");
			int itemCount = scan.nextInt();
			double amountSpend = 0.0;
			boolean flag = false;
			for (int i = 0; i < itemCount; i++) {
				System.out.println("Enter item id ");
				int itemId = scan.nextInt();

				boolean verify = true;
				for (ItemEntity value : item) {

					if (value.getItemId() == itemId) {
						if (value.getQuantity() > 0) {
							verify = false;
							System.out.println("Enter quantity");
							int quantity = scan.nextInt();
							if (value.getQuantity() >= quantity) {
								flag = true;
								price = value.getPrice() * quantity;
								amountSpend += price;

								int itemQuantity = value.getQuantity() - quantity;
								value.setQuantity(itemQuantity);

								setQuantity(itemQuantity, itemId);
							} else
								System.out.println("the quantity u want is not availabe");
						} else
							System.out.println("Item you want is unavailable:");
					}
				}
				if (verify)
					System.out.println("Enter a proper item id to purchase:");
			}
			if (flag) {
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
				String sdate = sd.format(date);
				CustomerShoppingEntity obj = new CustomerShoppingEntity(cust.getCustomerId(), "cust.getCustomerName()",
						"cust.getPhoneNumber()", sdate, amountSpend);
				shop.add(obj);
				int id = cust.getCustomerId();
				String name = cust.getCustomerName();
				String number = cust.getPhoneNumber();
				insertShoppingdetails(id, name, number, sdate, amountSpend);

			}
		} else
			System.out.println("Enter a proper customer Id:");
		return shop;

	}

	private void insertShoppingdetails(int customerId, String customerName, String contactNumber, String date,
			double price) throws Exception {
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String sree = "insert into cust_shopping values(" + customerId + "," + "'" + customerName + "'" + "," + "'"
				+ contactNumber + "'" + "," + "'" + date + "'" + "," + price + ")";
		st.executeUpdate(sree);
		connect.closeResource(st);
		connect.closeResource(con);

	}

	private void displayItems(Set<ItemEntity> item2) throws Exception {
		// TODO Auto-generated method stub

		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String query = "select * from item";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getInt("itemId") + " " + rs.getString("itemName") + " " + rs.getDouble("price") + " "
					+ rs.getInt("quantity"));
		}
		connect.closeResource(st);
		connect.closeResource(con);
	}

	public Set<CustomerShoppingEntity> shoppingReportGeneration(Set<CustomerShoppingEntity> shop) throws Exception {
		// Collections.sort((List<T>) shop);
		displayShoppingReport(shop);
		return shop;
	}

	private void displayShoppingReport(Set<CustomerShoppingEntity> shop) throws Exception {
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		System.out.println("Enter the date to display the customer");
		String date = scan.next();
		date = dateValidation(date);
		String query = "select * from cust_shopping where purchaseDate=" + "'" + date + "'" + "order by price";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(
					rs.getInt("customerId") + " " + rs.getString("customerName") + " " + rs.getString("contactNumber")
							+ " " + rs.getString("purchaseDate") + " " + rs.getDouble("price") + " ");
		}
		connect.closeResource(st);
		connect.closeResource(con);
	}

	private static String dateValidation(String date) {
		boolean isValid = false;
		while (isValid == false) {

			String sDay = "", sMonth = "", sYear = "";
			sDay = sDay + date.charAt(0);
			sDay = sDay + date.charAt(1);
			sMonth = sMonth + date.charAt(3);
			sMonth = sMonth + date.charAt(4);
			sYear = sYear + date.charAt(6);
			sYear = sYear + date.charAt(7);
			sYear = sYear + date.charAt(8);
			sYear = sYear + date.charAt(9);
			int day = Integer.parseInt(sDay);
			int month = Integer.parseInt(sMonth);
			int year = Integer.parseInt(sYear);
			int maxYear = 9999;
			int minYear = 1900;
			if (minYear <= year && year <= maxYear) {
				if ((year % 400 == 0 || year % 4 == 0) && month == 2) {
					if (day <= 29) {
						isValid = true;
					}
				} else {
					if (month == 2) {
						if (day <= 28) {
							isValid = true;
						}
					} else if (month == 4 || month == 6 || month == 9 || month == 11) {
						if (day <= 30)
							isValid = true;
					} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {
						if (day <= 31)
							isValid = true;
					} else
						isValid = false;
				}
			}
			if (isValid == false) {
				System.out.println("Please enter valid date");
				date = scan.next();
			}
		}
		return date;
	}

	public Set<ItemEntity> addItemsInDb(Set<ItemEntity> item) throws Exception {
		int itemId, quantity;
		String itemName;
		double itemPrice;
		System.out.println("Enter id:");
		itemId = integerValidate();
		System.out.println("Enter name:");
		itemName = scan.next();
		System.out.println("Enter price of an item");
		itemPrice = scan.nextDouble();
		System.out.println("Enter item quantity");
		quantity = integerValidate();
		ItemEntity emp = new ItemEntity(itemId, itemName, itemPrice, quantity);
		item.add(emp);
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String sree = "insert into item values(" + itemId + "," + "'" + itemName + "'" + "," + itemPrice + ","
				+ quantity + ")";
		st.executeUpdate(sree);
		connect.closeResource(st);
		connect.closeResource(con);
		return item;
	}

	public Set<ItemEntity> updateQuantity(Set<ItemEntity> item) throws Exception {

		System.out.println("Enter id:");
		int itemsId = integerValidate();
		boolean check = false;
		for (ItemEntity books : item) {
			if (books.getItemId() == itemsId) {
				check = true;
				System.out.println("Enter quantity to update:");
				int quantity = integerValidate();
				books.setQuantity(quantity);
				System.out.println("Quantity updated");
				setQuantity(quantity, itemsId);

			}
		}
		if (!check) {
			System.out.println("Enter a valid item id to update");
		}

		return item;
	}

	private void setQuantity(int quantity, int itemsId) throws Exception {
		// TODO Auto-generated method stub
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String update = "UPDATE item SET quantity=" + quantity + " where itemId=" + itemsId;
		st.executeUpdate(update);
		connect.closeResource(st);
		connect.closeResource(con);
	}

	public Set<ItemEntity> deleteItem(Set<ItemEntity> item) throws Exception {
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		System.out.println("Enter id:");
		int itemsId = integerValidate();
		boolean check = false;
		for (ItemEntity books : item) {
			if (books.getItemId() == itemsId) {
				check = true;
				item.remove(books);
				System.out.println("Quantity updated");
				String del = "DELETE from item where itemId=" + itemsId;
				st.executeUpdate(del);
			}
		}
		if (!check) {
			System.out.println("Enter a valid itemId to delete");
		}
		connect.closeResource(st);
		connect.closeResource(con);
		return item;
	}

	public void displayItemsByPriceAndQuantity(Set<ItemEntity> item) throws Exception {
		con = connect.getMyConnection();
		Statement st = con.createStatement();
		String query = "select * from item order by price,quantity";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getInt("itemId") + " " + rs.getString("itemName") + " " + rs.getDouble("price") + " "
					+ rs.getInt("quantity"));
		}
		connect.closeResource(st);
		connect.closeResource(con);

	}

}
