package com.oliveira.mdbspringboot.service;

import com.oliveira.mdbspringboot.dto.CategoryDTO;
import com.oliveira.mdbspringboot.model.GroceryItem;

import java.util.List;

public interface GroceryItemService {

    List<GroceryItem> save(List<GroceryItem> groceryItem);

    List<GroceryItem> update(List<GroceryItem> groceryItem);

    List<GroceryItem> findAll();

    GroceryItem findById(String id);

    GroceryItem findByName(String name);

    List<GroceryItem> findByCategory(String category);

    List<GroceryItem> updateCategoryName(CategoryDTO categoryDTO);

    void delete(String id);
}
