package com.example.sellit.service.item;

import com.example.sellit.model.Category;
import com.example.sellit.model.Item;
import com.example.sellit.model.Subcategory;
import com.example.sellit.repository.CategoryRepository;
import com.example.sellit.repository.ItemRepository;
import com.example.sellit.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public ItemServiceImp(ItemRepository itemRepository, CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public Item getItemById(Long ItemId) {
        return itemRepository.findById(ItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with id " + ItemId + " does not exist"));
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByCategory(Long CategoryId) {
        return itemRepository.findItemsByCategoryId(CategoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Subcategory addSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }
}
