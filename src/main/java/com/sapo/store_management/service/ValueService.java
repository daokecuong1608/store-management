package com.sapo.store_management.service;

import com.sapo.store_management.dto.ValueRequest;
import com.sapo.store_management.model.Value;
import com.sapo.store_management.repository.ValueRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public List<Value> getAllValues() {return valueRepo.findAll();}

    public Value getValueById(int id) {return valueRepo.findById(id).get();}

    public void insertValue(Value value) {valueRepo.save(value);}

    public void deleteValue(Value value) {valueRepo.delete(value);}

    public ValueRequest updateValue(int value_id, ValueRequest valueRequest) {
        Value value =  valueRepo.findById(value_id)
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
