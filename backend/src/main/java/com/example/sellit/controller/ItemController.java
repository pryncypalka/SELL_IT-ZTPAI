package com.example.sellit.controller;

import com.example.sellit.dto.CategoryDto;
import com.example.sellit.dto.ItemDto;
import com.example.sellit.model.Category;
import com.example.sellit.model.Item;
import com.example.sellit.service.item.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sellit.mapper.ItemMapper;
import com.example.sellit.mapper.CategoryMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ItemDto>> getItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemDto> itemsDto = items.stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        return new ResponseEntity<>(ItemMapper.toDto(item), HttpStatus.OK);
    }

    @GetMapping("/get-by-category/{categoryId}")
    public ResponseEntity<List<ItemDto>> getItemsByCategory(@PathVariable Long categoryId) {
        List<Item> items = itemService.getItemsByCategory(categoryId);
        List<ItemDto> itemsDto = items.stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = itemService.getAllCategories();
        List<CategoryDto> categoriesDto = categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoriesDto, HttpStatus.OK);
    }
}
