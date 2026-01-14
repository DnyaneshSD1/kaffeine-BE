package com.cafe.kaffeine.dao;

import com.cafe.kaffeine.entities.MenuItem;

import java.util.List;

public interface MenuItemDao {
    
    public List<MenuItem> getAllItems();

    public MenuItem addItem(MenuItem item);

    public List<MenuItem> addItems(List<MenuItem> items);

    public void deleteItem(MenuItem item);
}
