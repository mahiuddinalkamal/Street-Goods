package com.app.streetgoods.service;

import com.app.streetgoods.model.Coordinates;
import com.app.streetgoods.model.Item;
import com.app.streetgoods.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Iterable<Item> listAllItem() {
        return itemRepository.findAll();
    }

    public List<Item> listItemByNameOrKeywords(String name) {
        return itemRepository.findByNameOrKeywords(name);
    }

    public List<Item> listItemByLocation(Coordinates location, Double radius) {
        return itemRepository.findByLocation(location.getLat(),location.getLng(), radius);
    }

    public Item getItem(Long id) {
        return itemRepository.findByID(id);
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
