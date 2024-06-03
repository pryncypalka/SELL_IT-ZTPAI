package com.example.sellit.controller;

import com.example.sellit.dto.CategoryDto;
import com.example.sellit.dto.ItemDto;
import com.example.sellit.dto.SubcategoryDto;
import com.example.sellit.mapper.SubcategoryMapper;
import com.example.sellit.model.Category;
import com.example.sellit.model.Item;
import com.example.sellit.model.Subcategory;
import com.example.sellit.service.item.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/add-category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody Category category) {
        Category newCategory = itemService.addCategory(category);
        return new ResponseEntity<>(CategoryMapper.toDto(newCategory), HttpStatus.CREATED);
    }

    @PostMapping("/add-item")
    public ResponseEntity<ItemDto> addItem(@RequestBody Item item) {
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(ItemMapper.toDto(newItem), HttpStatus.CREATED);
    }

    @PostMapping("/add-subcategory")
    public ResponseEntity<SubcategoryDto> addSubcategory(@RequestBody Subcategory Subcategory) {
        Subcategory newCategory = itemService.addSubcategory(Subcategory);
        return new ResponseEntity<>(SubcategoryMapper.toDto(newCategory), HttpStatus.CREATED);
    }
}
