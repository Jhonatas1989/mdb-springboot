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
    private ItemRepository repository;

    @Override
    public List<GroceryItem> save(List<GroceryItem> groceryItem) {
        groceryItem.forEach(item -> repository.save(item));

        return findAll();
    }

    @Override
    public List<GroceryItem> update(List<GroceryItem> groceryItems) {
        groceryItems.forEach(item -> {
            var groceryItem = repository.findById(item.getId());

            if (groceryItem.isPresent()) {
                repository.delete(groceryItem.get());

                repository.save(item);
            }
        });

        return findAll();
    }

    @Override
    public List<GroceryItem> findAll() {
        return repository.findAll();
    }

    @Override
    public GroceryItem findById(String id) {
        Optional<GroceryItem> groceryItemOptional = repository.findById(id);

        return groceryItemOptional.orElse(null);
    }

    @Override
    public GroceryItem findByName(String name) {
        return repository.findItemByName(name);
    }

    @Override
    public List<GroceryItem> findByCategory(String category) {
        return repository.findAll(category);
    }


    @Override
    public List<GroceryItem> updateCategoryName(CategoryDTO categoryDTO) {
        List<GroceryItem> list = repository.findAll(categoryDTO.getName());

        list.forEach(item -> {
            item.setCategory(categoryDTO.getNewName());
        });

        return repository.saveAll(list);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
