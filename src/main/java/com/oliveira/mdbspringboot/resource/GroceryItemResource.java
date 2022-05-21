package com.oliveira.mdbspringboot.resource;

import com.oliveira.mdbspringboot.dto.CategoryDTO;
import com.oliveira.mdbspringboot.model.GroceryItem;
import com.oliveira.mdbspringboot.service.GroceryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class GroceryItemResource {

    private final GroceryItemService service;

    public GroceryItemResource(GroceryItemService service) {
        this.service = service;
    }

    @GetMapping("/grocery-items")
    public List<GroceryItem> findAll() {
        return service.findAll();
    }

    @GetMapping("/grocery-items/{id}")
    public GroceryItem findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/grocery-items/name/{name}")
    public GroceryItem findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

    @GetMapping("/grocery-items/category/{category}")
    public List<GroceryItem> findByCategory(@PathVariable("category") String category) {
        return service.findByCategory(category);
    }

    @PostMapping("/grocery-items")
    public List<GroceryItem> save(@Validated @RequestBody List<GroceryItem> items) {
        return service.save(items);
    }

    @PutMapping("/grocery-items")
    public List<GroceryItem> update(@Validated @RequestBody List<GroceryItem> items) {
        return service.update(items);
    }

    @PutMapping("/grocery-items/category")
    public List<GroceryItem> updateCategoryName(@Validated @RequestBody CategoryDTO categoryDTO) {
        return service.updateCategoryName(categoryDTO);
    }

    @DeleteMapping("/grocery-items/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);

        return new ResponseEntity<String>("Grocery Item was deleted successfully", HttpStatus.OK);
    }
}
