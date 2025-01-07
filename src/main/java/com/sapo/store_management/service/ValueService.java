package com.sapo.store_management.service;

import com.sapo.store_management.dto.ValueRequest;
import com.sapo.store_management.model.Value;
import com.sapo.store_management.repository.ValueRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ValueService {
    private static final Logger log = LoggerFactory.getLogger(ValueService.class);
    private final ValueRepo valueRepo;

    public ValueService(ValueRepo valueRepo) {
        this.valueRepo = valueRepo;
    }

    public Page<Value> getAllValues(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Value> value = valueRepo.findAll(pageable);
        return value;
    }

    public Value getValueById(int id) {
        Value va = valueRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found value"));
        return va;
    }

    public Value insertValue(Value value) {
        Value insert = valueRepo.save(value);
        return insert;
    }

    public void deleteValue(int id) {
        Value va = valueRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found value"));
        valueRepo.delete(va);
    }

    public ValueRequest updateValue(int value_id, ValueRequest valueRequest) {
        Value value = valueRepo.findById(value_id)
                .orElseThrow(() -> new RuntimeException("Value not found"));
        log.info(value.getName());
        value.setName(valueRequest.getName());
        value.setPosition(valueRequest.getPosition());
        value.setUpdated_at(new Date(System.currentTimeMillis()));

        Value update = valueRepo.save(value);

        return new ValueRequest(
                update.getId(),
                update.getOption_id(),
                update.getName(),
                update.getPosition()
        );
    }
}
