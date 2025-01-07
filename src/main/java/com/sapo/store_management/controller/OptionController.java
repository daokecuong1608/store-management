package com.sapo.store_management.controller;

import com.sapo.store_management.dto.OptionRequest;
import com.sapo.store_management.model.Option;
import com.sapo.store_management.service.OptionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/option")
public class OptionController {
    private static final Logger log = LoggerFactory.getLogger(OptionController.class);
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Option>> getAllOptions(@RequestParam int page,
                                                      @RequestParam int size,
                                                      @RequestParam(defaultValue = "id") String sortBy) {
        Page<Option> options = optionService.getAllOptions(page, size, sortBy);

        return ResponseEntity.ok(options);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable int id) {
        Option option = optionService.getOptionById(id);
        if (option == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(option);
    }

    @PostMapping("/insert")
    public ResponseEntity<Option> insertOption(@Valid @RequestBody Option option) {
        Option insertOption = optionService.insertOption(option);
        if (insertOption == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(insertOption);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<OptionRequest> updateOption(@PathVariable int id, @RequestBody OptionRequest optionRequest) {
        OptionRequest request = optionService.updateOption(id, optionRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOption(@RequestBody int id) {
        optionService.deleteOption(id);
        return ResponseEntity.ok().build();
    }
}
