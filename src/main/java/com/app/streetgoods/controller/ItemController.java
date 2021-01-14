package com.app.streetgoods.controller;

import com.app.streetgoods.model.Coordinates;
import com.app.streetgoods.model.Item;
import com.app.streetgoods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping("")
    public Iterable<Item> listAll() {
        return itemService.listAllItem();
    }
    @GetMapping("/name")
    public ResponseEntity<List<Item>> listByNameOrKeywords(@RequestParam("name") String name) {
        try{
            List<Item> items = itemService.listItemByNameOrKeywords(name);
            return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/location")
    public ResponseEntity<List<Item>> listByLocation(Coordinates location, @RequestParam("rad") double radius){
        try{
            List<Item> items = itemService.listItemByLocation(location, radius);
            return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        try {
            Item  item = itemService.getItem(id);
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Item item) {
        itemService.saveItem(item);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Item item, @PathVariable Long id) {
        try {
            Item existItem = itemService.getItem(id);
            item.setId(id);
            itemService.saveItem(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
