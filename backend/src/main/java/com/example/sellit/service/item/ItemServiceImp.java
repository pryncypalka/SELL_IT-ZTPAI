package com.example.sellit.service.item;

import com.example.sellit.model.Item;
import com.example.sellit.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepository;

    public ItemServiceImp(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }


    @Override
    public Item getItemById(Long ItemId) {
        return itemRepository.findById(ItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with id " + ItemId + " does not exist"));
    }
}
