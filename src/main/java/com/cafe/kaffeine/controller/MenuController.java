package com.cafe.kaffeine.controller;

import com.cafe.kaffeine.entities.MenuItem;
import com.cafe.kaffeine.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController extends BaseController{

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public ResponseEntity<List<MenuItem>> showMenu() {
        try {
            return ResponseEntity.ok(menuService.getAllItems());
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/add/item")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
        try {
        return ResponseEntity.ok(menuService.addItem(item));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/item")
    public ResponseEntity<String> deleteMenuItem(@RequestBody MenuItem item) {
        try {
            menuService.deleteItem(item);
            return ResponseEntity.ok().body("Item deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/edit/item")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem item) {
        try {
            return ResponseEntity.ok(menuService.updateItem(item));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
