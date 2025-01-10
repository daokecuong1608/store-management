package com.sapo.store_management.service.tag;


import com.sapo.store_management.dto.tag.TagRequest;
import com.sapo.store_management.dto.tag.TagResponse;
import org.springframework.data.domain.Page;

public interface TagService {

    Page<TagResponse> getAllTag(int page , int size , String sortBy);

    TagResponse getById(Integer id);

    TagResponse save(TagRequest tagRequest);

    TagResponse update(Integer id, TagRequest tagRequest);

    void deleteById(Integer id);

}
