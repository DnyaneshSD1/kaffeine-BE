package com.cafe.kaffeine.service;

import com.cafe.kaffeine.entities.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> getAllItems();

    MenuItem addItem(MenuItem item);

    List<MenuItem> addItems(List<MenuItem> items);

    void deleteItem(MenuItem item);

    MenuItem updateItem(MenuItem item);
}
