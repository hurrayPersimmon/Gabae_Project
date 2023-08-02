package com.hurray.gabae.dto;


import com.hurray.gabae.entity.SuggestEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SuggestForm {

    private String userId;
    private String suggestionContent;
    public SuggestEntity toEntity() { return new SuggestEntity(userId, suggestionContent);  }


}
