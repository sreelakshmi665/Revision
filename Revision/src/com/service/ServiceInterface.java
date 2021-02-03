package com.service;

import java.util.Set;

import com.entity.CustomerEntity;
import com.entity.CustomerShoppingEntity;
import com.entity.ItemEntity;

public interface ServiceInterface {
void GetAllItemsBasedOnPriceAndQuantity(Set<ItemEntity> item) throws Exception;
Set<ItemEntity> deleteItem(Set<ItemEntity> item) throws Exception;
Set<ItemEntity> updateItemsQuantity(Set<ItemEntity> item) throws Exception;
Set<ItemEntity> addItems(Set<ItemEntity> item) throws Exception;
Set<CustomerShoppingEntity> generateShoppingReport(Set<CustomerShoppingEntity> shop) throws Exception;
Set<CustomerShoppingEntity> shop(Set<CustomerEntity> set, Set<ItemEntity> item) throws Exception;
Set<CustomerEntity> signUp(Set<CustomerEntity> set) throws Exception ;
		
}
