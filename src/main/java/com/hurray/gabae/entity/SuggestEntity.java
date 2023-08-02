package com.hurray.gabae.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Getter
@Setter
public class SuggestEntity extends BaseTimeEntity{
    @Id
//    @JoinColumn
    private String userId;
    private String suggestionContent;
    public void setting(SuggestEntity user){
        if(user.suggestionContent != null)
            this.suggestionContent = suggestionContent;
    }
}