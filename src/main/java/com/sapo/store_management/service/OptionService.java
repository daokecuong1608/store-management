package com.sapo.store_management.service;

import com.sapo.store_management.model.Option;
import com.sapo.store_management.repository.OptionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepo optionRepo;
    public OptionService(OptionRepo optionRepo) {
        this.optionRepo = optionRepo;
    }

    public List<Option> getAllOptions() {return optionRepo.findAll();}

    public Option getOptionById(int id) {return optionRepo.findById(id).get();}

    public void insertOption(Option option) {optionRepo.save(option);}

    public void deleteOption(Option option) {optionRepo.delete(option);}
}
