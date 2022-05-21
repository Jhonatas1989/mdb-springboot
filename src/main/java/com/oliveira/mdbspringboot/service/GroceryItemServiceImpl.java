package com.oliveira.mdbspringboot.service;

import com.oliveira.mdbspringboot.dto.CategoryDTO;
import com.oliveira.mdbspringboot.model.GroceryItem;
import com.oliveira.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    @Autowired
    ItemRepository groceryItemRepo;

    @Override
    public List<GroceryItem> save(List<GroceryItem> groceryItem) {
        groceryItem.forEach(item -> groceryItemRepo.save(item));

        return findAll();
    }

    @Override
    public List<GroceryItem> update(List<GroceryItem> groceryItems) {
        groceryItems.forEach(item -> {
            var groceryItem = groceryItemRepo.findById(item.getId());

            if (groceryItem.isPresent()) {
                groceryItemRepo.delete(groceryItem.get());

                groceryItemRepo.save(item);
            }
        });

        return findAll();
    }

    @Override
    public List<GroceryItem> findAll() {
        return groceryItemRepo.findAll();
    }

    @Override
    public GroceryItem findById(String id) {
        Optional<GroceryItem> groceryItemOptional = groceryItemRepo.findById(id);

        return groceryItemOptional.orElse(null);
    }

    @Override
    public GroceryItem findByName(String name) {
        return groceryItemRepo.findItemByName(name);
    }

    @Override
    public List<GroceryItem> findByCategory(String category) {
        return groceryItemRepo.findAll(category);
    }


    @Override
    public List<GroceryItem> updateCategoryName(CategoryDTO categoryDTO) {
        List<GroceryItem> list = groceryItemRepo.findAll(categoryDTO.getName());

        list.forEach(item -> {
            item.setCategory(categoryDTO.getNewName());
        });

        return groceryItemRepo.saveAll(list);
    }

    @Override
    public void delete(String id) {
        groceryItemRepo.deleteById(id);
    }
}
