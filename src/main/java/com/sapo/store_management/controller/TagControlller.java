package com.sapo.store_management.controller;

import com.sapo.store_management.model.Tag;
import com.sapo.store_management.service.TagService;
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

    @DeleteMapping("/delete")
    public void deleteTag(@RequestBody Tag tag) {tagService.deleteTag(tag);}
}
