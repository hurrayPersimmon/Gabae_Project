package com.hurray.gabae.service;

import com.hurray.gabae.dto.SuggestForm;
import com.hurray.gabae.entity.SuggestEntity;
import com.hurray.gabae.repository.SuggestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SuggestService {
    @Autowired
    private SuggestRepository suggestRepository;

    // 프로필 보기
    public SuggestEntity showPage(String userId) {
        return suggestRepository.findById(userId).orElse(null);
    }

    // 프로필 설정
    public SuggestEntity setPage(SuggestForm dto) {
        SuggestEntity user = dto.toEntity();
//        if (user.getSuggestionContent().length() >8){
//            user.setSuggestionContent("suggest content over");
//            return user;
//        }
        return suggestRepository.save(user);

    }


    // 프로필 수정
    public SuggestEntity updatePage(String userId, SuggestForm dto) {
        SuggestEntity user = dto.toEntity();
        SuggestEntity target = suggestRepository.findById(userId).orElse(null);
//        if (target.getSuggestionContent().length() >8){
//            target.setSuggestionContent("suggest content over");
//            return target;
//        }
        if (target == null || userId != user.getUserId()){ // 값이 반화되지 않거나, UserId가 다를 때
            log.info("wrong request! id : {}, data : {}", userId, user.toString());
            return null;
        }
        user.setting(target);
        SuggestEntity suggestUpdated = suggestRepository.save(user);
        return suggestUpdated;
    }
}
