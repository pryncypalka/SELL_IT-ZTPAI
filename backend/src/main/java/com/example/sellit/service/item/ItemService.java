package com.example.sellit.service.item;

import com.example.sellit.model.Category;
import com.example.sellit.model.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(Long ItemId);

    Item addItem(Item item);

    List<Item> getAllItems();

    List<Item> getItemsByCategory(Long CategoryId);

    List<Category> getAllCategories();
}
