package com.sapo.store_management.service;

import com.sapo.store_management.dto.TagRequest;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.repository.TagRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepo tagRepo;
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public List<Tag> getAllTags() {return tagRepo.findAll();}

    public Tag getTagById(int id) {return tagRepo.getReferenceById(id);}

    public void insertTag(Tag tag) {tagRepo.save(tag);}

    public void deleteTag(Tag tag) {tagRepo.delete(tag);}

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
