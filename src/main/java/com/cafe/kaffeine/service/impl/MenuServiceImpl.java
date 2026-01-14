package com.cafe.kaffeine.service.impl;

import com.cafe.kaffeine.entities.MenuItem;
import com.cafe.kaffeine.repository.MenuItemRepository;
import com.cafe.kaffeine.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuItemRepository repository;

    @Override
    public List<MenuItem> getAllItems() {
        return repository.findAll();
    }

    @Override
    public MenuItem addItem(MenuItem item) {
        return repository.save(item);
    }

    @Override
    public List<MenuItem> addItems(List<MenuItem> items) {
        return repository.saveAll(items);
    }

    @Override
    public void deleteItem(MenuItem item) {
        repository.delete(item);
    }

    @Override
    public MenuItem updateItem(MenuItem item) {
        MenuItem existing = repository.findById(item.getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        String category = item.getCategory() != null ? item.getCategory() : existing.getCategory();
        double price = item.getPrice() != null ? item.getPrice() : existing.getPrice();
        String description = item.getDescription() != null ? item.getDescription() : existing.getDescription();
        String name = item.getName() != null ? item.getName() : existing.getName();
        boolean available = item.isAvailable();
        existing.setCategory(category);
        existing.setPrice(price);
        existing.setDescription(description);
        existing.setName(name);
        existing.setAvailable(available);
        return repository.save(existing);
    }
}
