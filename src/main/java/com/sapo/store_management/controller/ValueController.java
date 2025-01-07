package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ValueRequest;
import com.sapo.store_management.model.Value;
import com.sapo.store_management.service.ValueService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/value")
public class ValueController {
    private final ValueService valueService;

    public ValueController(ValueService valueService) {
        this.valueService = valueService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Value>> getAllValues(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Page<Value> values = valueService.getAllValues(page, size, sortBy);
        return ResponseEntity.ok(values);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Value> getValueById(@PathVariable int id) {
        Value value = valueService.getValueById(id);
        if (value != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Value> insertValue(@Valid @RequestBody Value value) {
        Value val = valueService.insertValue(value);
        if (val == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(val);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ValueRequest> updateValue(@Valid @PathVariable int id, @Valid @RequestBody ValueRequest value) {
        ValueRequest request = valueService.updateValue(id, value);
        if (request != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteValue(@PathVariable int id) {
        valueService.deleteValue(id);
        return ResponseEntity.ok().build();
    }
}
