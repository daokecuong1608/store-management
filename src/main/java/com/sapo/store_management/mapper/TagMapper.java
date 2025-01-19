package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.tag.TagRequest;
import com.sapo.store_management.dto.tag.TagResponse;
import com.sapo.store_management.model.Tag;

public class TagMapper {

    public static TagResponse convertTag(Tag tag){
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .created_at(tag.getCreated_at())
                .updated_at(tag.getUpdated_at())
                .build();
    }
    public static Tag convertTagResponse(TagRequest tagRequest){
        return Tag.builder()
                .name(tagRequest.getName())
                .build();
    }

}
