package com.sapo.store_management.service;

import com.sapo.store_management.dto.OptionRequest;
import com.sapo.store_management.model.Option;
import com.sapo.store_management.repository.OptionRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OptionService {
    private final OptionRepo optionRepo;

    public OptionService(OptionRepo optionRepo) {
        this.optionRepo = optionRepo;
    }

    public Page<Option> getAllOptions(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Option> result = optionRepo.findAll(pageable);
        return result;
    }

    public Option getOptionById(int id) {
        Option option = optionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not find"));
        return option;
    }

    public Option insertOption(Option option) {
        Option insertOption = optionRepo.save(option);
        return insertOption;
    }

    public void deleteOption(int id) {
        Option option = optionRepo.findById(id).orElseThrow(() -> new RuntimeException("Option not found"));
        optionRepo.delete(option);
    }

    public OptionRequest updateOption(int id, OptionRequest optionRequest) {
        Option option = optionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found"));

        option.setName(optionRequest.getName());
        option.setProduct_id(optionRequest.getProduct_id());
        option.setUpdated_at(new Date(System.currentTimeMillis()));

        Option update = optionRepo.save(option);

        return new OptionRequest(
                update.getId(),
                update.getProduct_id(),
                update.getName());
    }
}
