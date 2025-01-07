package com.sapo.store_management.controller;

import com.sapo.store_management.dto.TagRequest;
import com.sapo.store_management.model.Tag;
import com.sapo.store_management.service.TagService;
import jakarta.validation.Valid;
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
    public List<Tag> getAllTags() {return tagService.getAllTags();}

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable int id) {return tagService.getTagById(id);}

    @PostMapping("/insert")
    public void insertTag(@RequestBody Tag tag) {tagService.insertTag(tag);}

    @PutMapping("/update/{id}")
    public ResponseEntity<TagRequest> updateTad(@Valid @PathVariable int id, @RequestBody TagRequest tagRequest){
        TagRequest request = tagService.updateTag(id, tagRequest);
        if(request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public void deleteTag(@RequestBody Tag tag) {tagService.deleteTag(tag);}
}
