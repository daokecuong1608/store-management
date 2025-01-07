package com.sapo.store_management.controller;

import com.sapo.store_management.model.Option;
import com.sapo.store_management.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<Option> getAllOptions() {return optionService.getAllOptions();}

    @GetMapping("/{id}")
    public Option getOptionById(@PathVariable int id) {return optionService.getOptionById(id);}

    @PostMapping("/insert")
    public void insertOption(@RequestBody Option option) {optionService.insertOption(option);}

    @DeleteMapping("/delete")
    public void deleteOption(@RequestBody Option option) {optionService.deleteOption(option);}
}
