package com.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.daoLayer.DaoImplementation;
import com.entity.CustomerEntity;
import com.entity.CustomerShoppingEntity;
import com.entity.ItemEntity;

public class ServiceImplementation implements ServiceInterface{
	DaoImplementation dao=new DaoImplementation();
	static Scanner sc=new Scanner(System.in);

	@Override
	public void GetAllItemsBasedOnPriceAndQuantity(Set<ItemEntity> item) throws Exception {
		dao.displayItemsByPriceAndQuantity(item);
		
	}

	@Override
	public Set<ItemEntity> deleteItem(Set<ItemEntity> item) throws Exception {
		item=dao.deleteItem(item);
		return item;
	}

	@Override
	public Set<ItemEntity> updateItemsQuantity(Set<ItemEntity> item) throws Exception {
		item=dao.updateQuantity(item);
		return item;
	}

	@Override
	public Set<ItemEntity> addItems(Set<ItemEntity> item) throws Exception {
		item=dao.addItemsInDb(item);
		return item;
		
	}

	@Override
	public Set<CustomerShoppingEntity> generateShoppingReport(Set<CustomerShoppingEntity> shop) throws Exception {
		//Collections.sort((List<CustomerShoppingEntity>) shop);
		shop=dao.shoppingReportGeneration(shop);
		return shop;
	}

	@Override
	public Set<CustomerShoppingEntity> shop(Set<CustomerEntity> set, Set<ItemEntity> item) throws Exception {
		Set<CustomerShoppingEntity> shop=new HashSet<>();
		shop=dao.shoppingDetails(set,item);
		return shop;
	}
		

	

	@Override
	public Set<CustomerEntity> signUp(Set<CustomerEntity> set) throws Exception {
		set=dao.Register(set);
		return set;
	}

}
