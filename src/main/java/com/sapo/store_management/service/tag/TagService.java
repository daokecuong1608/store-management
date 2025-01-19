package com.sapo.store_management.service.tag;


import com.sapo.store_management.dto.tag.TagRequest;
import com.sapo.store_management.dto.tag.TagResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {

    Page<TagResponse> getAllTag(int page , int size , String sortBy);

    List<TagResponse> getAllTags();

    TagResponse getById(Integer id);

    TagResponse save(TagRequest tagRequest);

    TagResponse update(Integer id, TagRequest tagRequest);

    void deleteById(Integer id);

}
