package com.plays.grocery.service;

import com.plays.grocery.model.GroceryItem;
import com.plays.grocery.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public GroceryItem create(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    @Override
    public List<GroceryItem> getAll() {
        return groceryItemRepository.findAll();
    }

    @Override
    public GroceryItem getById(Long id) {
        return groceryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public GroceryItem update(Long id, GroceryItem item) {
        GroceryItem existing = getById(id);

        existing.setName(item.getName());
        existing.setCategory(item.getCategory());
        existing.setPrice(item.getPrice());
        existing.setQuantity(item.getQuantity());

        return groceryItemRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        groceryItemRepository.deleteById(id);
    }
}