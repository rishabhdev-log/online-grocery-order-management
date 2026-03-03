package com.plays.grocery.controller;

import com.plays.grocery.model.GroceryItem;
import com.plays.grocery.service.GroceryItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @PostMapping
    public GroceryItem create(@RequestBody GroceryItem item) {
        return groceryItemService.create(item);
    }

    @GetMapping
    public List<GroceryItem> getAll() {
        return groceryItemService.getAll();
    }

    @GetMapping("/{id}")
    public GroceryItem getById(@PathVariable Long id) {
        return groceryItemService.getById(id);
    }

    @PutMapping("/{id}")
    public GroceryItem update(@PathVariable Long id, @RequestBody GroceryItem item) {
        return groceryItemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groceryItemService.delete(id);
    }
}