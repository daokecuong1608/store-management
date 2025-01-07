package com.sapo.store_management.service;

import com.sapo.store_management.dto.TagRequest;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.repository.TagRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepo tagRepo;

    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Page<Tag> getAllTags(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Tag> tag = tagRepo.findAll(pageable);
        return tag;
    }

    public Tag getTagById(int id) {
        Tag tag = tagRepo.findById(id).orElseThrow(() -> new RuntimeException("Tag not find"));
        return tag;
    }

    public Tag insertTag(Tag tag) {
        Tag insertTag = tagRepo.save(tag);
        return insertTag;
    }

    public void deleteTag(int id) {
        Tag tag = tagRepo.findById(id).orElseThrow(() -> new RuntimeException("Tag not find"));
        tagRepo.delete(tag);
    }

    public TagRequest updateTag(int id, TagRequest tagRequest) {
        Tag tag = tagRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        tag.setName(tagRequest.getName());

        Tag update = tagRepo.save(tag);

        return new TagRequest(
                update.getId(),
                update.getName());
    }
}
