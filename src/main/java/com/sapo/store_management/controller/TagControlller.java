package com.sapo.store_management.controller;

import com.sapo.store_management.dto.tag.TagRequest;
import com.sapo.store_management.dto.tag.TagResponse;
import com.sapo.store_management.service.tag.TagService;
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
    public ResponseEntity<Page<TagResponse>> getAllTags(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<TagResponse> tag = tagService.getAllTag(page, size, sortBy);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tagResponses =  tagService.getAllTags();
        return ResponseEntity.ok(tagResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Integer id) {
        TagResponse tag = tagService.getById(id);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TagResponse> insertTag(@Valid @RequestBody TagRequest tagRequest) {
        TagResponse insert = tagService.save(tagRequest);
        if (insert != null) {
            return ResponseEntity.ok(insert);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTad(@PathVariable Integer id, @Valid @RequestBody TagRequest tagRequest) {
        TagResponse request = tagService.update(id, tagRequest);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer id) {
        tagService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
