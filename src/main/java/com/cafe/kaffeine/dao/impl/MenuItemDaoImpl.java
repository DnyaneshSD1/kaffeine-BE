package com.cafe.kaffeine.dao.impl;

import com.cafe.kaffeine.dao.MenuItemDao;
import com.cafe.kaffeine.entities.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemDaoImpl implements MenuItemDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<MenuItem> getAllItems() {
       return mongoTemplate.findAll(MenuItem.class);
    }

    @Override
    public MenuItem addItem(MenuItem item) {
        return mongoTemplate.save(item, "menu_items");
    }

    @Override
    public List<MenuItem> addItems(List<MenuItem> items) {
        List<MenuItem> saved = new ArrayList<>();
        for (MenuItem item : items) {
            saved.add(mongoTemplate.save(item, "menu_items"));
        }
        return saved;
    }

    @Override
    public void deleteItem(MenuItem item) {
        mongoTemplate.remove(findItemByName(item));
    }

    MenuItem findItemByName(MenuItem item) {
        Query query = new Query(
                Criteria.where("name").is(item.getName())
                        .and("category").is(item.getCategory())
                        .and("description").is(item.getDescription())
                        .and("price").is(item.getPrice())
        );
        return mongoTemplate.findOne(query, MenuItem.class);
    }
}
