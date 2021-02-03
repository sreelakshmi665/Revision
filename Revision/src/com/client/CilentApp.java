package com.client;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.service.ServiceImplementation;

import com.entity.CustomerEntity;
import com.entity.CustomerShoppingEntity;
import com.entity.ItemEntity;
public class CilentApp {
	static Scanner scan=new Scanner(System.in);
public static void main(String[] args) throws Exception {
	Set<CustomerEntity> set=new HashSet<>();
	Set<ItemEntity> item=new HashSet<>();
	Set<CustomerShoppingEntity> shop=new HashSet<>();
	ServiceImplementation service=new ServiceImplementation();
	
	boolean check=true;
	do {
		System.out.println("Enter your choice:");
		System.out.println("1.Signup customers\n2.Shop\n3.Generate shooping report\n4.add item\n5.update items's quantity\n6.delete the item\n"
				+ "7.get all the items and print in sorted order by price and quantity\n 8.Exit");
		int choice=scan.nextInt();
		switch(choice)
		{
		case 1:set=service.signUp(set);
			break;
		case 2:
			shop=service.shop(set,item);
			break;
		case 3:shop=service.generateShoppingReport(shop);
			break;
		case 4:item=service.addItems(item);
			break;
		case 5:item=service.updateItemsQuantity(item);
			break;
		case 6:item=service.deleteItem(item);
			break;
		case 7:service.GetAllItemsBasedOnPriceAndQuantity(item);
			break;
		case 8:check=false;
			break;
		}
	}while(check);
}

}
