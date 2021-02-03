package com.entity;

public class ItemEntity {
private int itemId;
private String itemName;
private double price;
private int quantity;
public ItemEntity(int itemId, String itemName, double price, int quantity) {
	super();
	this.itemId = itemId;
	this.itemName = itemName;
	this.price = price;
	this.quantity = quantity;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public void setPrice(double price) {
	this.price = price;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getItemId() {
	return itemId;
}
public String getItemName() {
	return itemName;
}
public double getPrice() {
	return price;
}
public int getQuantity() {
	return quantity;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + itemId;
	result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ItemEntity other = (ItemEntity) obj;
	if (itemId != other.itemId)
		return false;
	if (itemName == null) {
		if (other.itemName != null)
			return false;
	} else if (!itemName.equals(other.itemName))
		return false;
	return true;
}
@Override
public String toString() {
	return "ItemEntity [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity
			+ "]";
}

}
