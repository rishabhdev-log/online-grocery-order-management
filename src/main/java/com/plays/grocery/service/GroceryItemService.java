package com.plays.grocery.service;

import com.plays.grocery.model.GroceryItem;
import java.util.List;

public interface GroceryItemService {

    GroceryItem create(GroceryItem item);

    List<GroceryItem> getAll();

    GroceryItem getById(Long id);

    GroceryItem update(Long id, GroceryItem item);

    void delete(Long id);
}