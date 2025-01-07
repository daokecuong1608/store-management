package com.sapo.store_management.controller;

import com.sapo.store_management.dto.TagRequest;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.service.TagService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tag")
public class TagControlller {
    private final TagService tagService;

    public TagControlller(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Tag>> getAllTags(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<Tag> tag = tagService.getAllTags(page, size, sortBy);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable int id) {
        Tag tag = tagService.getTagById(id);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<Tag> insertTag(@Valid @RequestBody Tag tag) {
        Tag insert = tagService.insertTag(tag);
        if (insert != null) {
            return ResponseEntity.ok(insert);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TagRequest> updateTad(@Valid @PathVariable int id, @RequestBody TagRequest tagRequest) {
        TagRequest request = tagService.updateTag(id, tagRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }
}
