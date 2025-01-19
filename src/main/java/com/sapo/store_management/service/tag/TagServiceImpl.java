package com.sapo.store_management.service.tag;

import com.sapo.store_management.dto.tag.TagRequest;
import com.sapo.store_management.dto.tag.TagResponse;
import com.sapo.store_management.mapper.TagMapper;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.repository.TagRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    public TagServiceImpl(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Override
    public Page<TagResponse> getAllTag(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Tag> tags = tagRepo.findAll(pageable);
        return tags.map(TagMapper::convertTag);
    }

    @Override
    public List<TagResponse> getAllTags(){
        List<Tag> tags = tagRepo.findAll();
        List<TagResponse> tagResponses = new ArrayList<>();
        for(Tag tag : tags) {
            tagResponses.add(TagMapper.convertTag(tag));
        }
        return tagResponses;
    }

    @Override
    public TagResponse getById(Integer id) {
        Tag tag = tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tag"));
        return TagMapper.convertTag(tag);
    }

    @Override
    public TagResponse save(TagRequest tagRequest) {
        Tag tag = TagMapper.convertTagResponse(tagRequest);
        tag = tagRepo.save(tag);
        TagResponse tagResponse = TagMapper.convertTag(tag);
        return tagResponse;
    }

    @Override
    public TagResponse update(Integer id, TagRequest tagRequest) {
        Tag tag = tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tag"));
        tag.setName(tagRequest.getName());
        tag.setUpdated_at(LocalDateTime.now());
        tag = tagRepo.save(tag);
        TagResponse tagResponse = TagMapper.convertTag(tag);
        return tagResponse;
    }

    @Override
    public void deleteById(Integer id) {
        Tag tag = tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tag"));
        tagRepo.delete(tag);
    }
}
