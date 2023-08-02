package com.hurray.gabae.api;


import com.hurray.gabae.dto.SuggestForm;
import com.hurray.gabae.entity.SuggestEntity;
import com.hurray.gabae.response.Response;
import com.hurray.gabae.service.SuggestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SuggsetApiController {
    @Autowired
    private SuggestService SuggestService;

    // 프로필 보기
//    @GetMapping("/suggest/{userId}")
//    public Response showPage(@PathVariable String userId){
//        UserEntity shown = userService.showPage(userId);
//        return (shown != null) ?
//                new Response(true, HttpStatus.OK.value(), "", shown) :
//                new Response(false, HttpStatus.BAD_REQUEST.value(),"" ,null);
//
//    }

    // 문의사항 글 작성
    @PostMapping("/suggest/post")
    public Response setPage(@RequestBody SuggestForm dto){
        SuggestEntity setting = SuggestService.setPage(dto);
//        if(setting.getSuggestionContent().equals("nickname over"))
//            return new Response(false, 301, "닉네임은 8자가 최대입니다.", null);
        return (setting != null) ?
                new Response(true, HttpStatus.OK.value(), "문의사항 글 작성 완료", setting.getUserId()) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"접근 오류" ,null);

    }

    // 문의사항 변경
    @PutMapping("/suggest/update/{userId}")
    public Response updatePage(@PathVariable String userId, @RequestBody SuggestForm dto){
        SuggestEntity updated = SuggestService.updatePage(userId, dto);
//        if(updated.getSuggestionContent().equals("nickname over"))
//            return new Response(false, 301, "닉네임은 8자가 최대입니다.", null);
        return (updated != null) ?
                new Response(true, HttpStatus.OK.value(), "문의사항 글 작성 완료", updated) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"" ,null);

    }
}
