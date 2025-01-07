package com.sapo.store_management.controller;

import com.sapo.store_management.dto.ValueRequest;
import com.sapo.store_management.model.Value;
import com.sapo.store_management.service.ValueService;
import jakarta.validation.Valid;
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
    public List<Value> getAllValues() {return this.valueService.getAllValues();}

    @GetMapping("/{id}")
    public Value getValueById(@PathVariable int id) {return this.valueService.getValueById(id);}

    @PostMapping("/insert")
    public void insertValue(@RequestBody Value value) {this.valueService.insertValue(value);}

    @PutMapping("/update/{id}")
    public ResponseEntity<ValueRequest> updateValue(@Valid @PathVariable int id, @Valid @RequestBody ValueRequest value) {
        ValueRequest request = valueService.updateValue(id, value);
        if(request != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteValue(@RequestBody Value value) {this.valueService.deleteValue(value);}
}
