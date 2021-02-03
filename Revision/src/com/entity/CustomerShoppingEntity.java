package com.entity;



public class CustomerShoppingEntity implements Comparable<CustomerShoppingEntity>{
private int customerId;
private String customerName;
private String contactnumber;
private String date;
private double price;
public CustomerShoppingEntity(int customerId, String customerName, String contactnumber, String date, double price) {
	super();
	this.customerId = customerId;
	this.customerName = customerName;
	this.contactnumber = contactnumber;
	this.date = date;
	this.price = price;
}

public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getContactnumber() {
	return contactnumber;
}
public void setContactnumber(String contactnumber) {
	this.contactnumber = contactnumber;
}
public double getPrice() {
	return price;
}


public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public void setPrice(double price) {
	this.price = price;
}

@Override
public int compareTo(CustomerShoppingEntity o) {
	return (int)(this.price-o.price);
}

}
