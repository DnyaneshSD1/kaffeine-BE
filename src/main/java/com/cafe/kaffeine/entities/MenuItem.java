package com.cafe.kaffeine.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "menu_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private boolean available;
}

