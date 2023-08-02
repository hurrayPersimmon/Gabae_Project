package com.hurray.gabae.api;


import com.hurray.gabae.dto.ChatRoomDto;
import com.hurray.gabae.dto.DeliveryForm;
import com.hurray.gabae.entity.Delivery;
import com.hurray.gabae.service.DeliveryService;
import com.hurray.gabae.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
public class DeliveryApiController {
    private final DeliveryService deliveryService;

    // 조회
    @GetMapping("/get")
    public Response index(){
        return(deliveryService.index()!= null) ?
             new Response(true, HttpStatus.OK.value(), "Welcome to GaBae", deliveryService.index()) :
             new Response (false, HttpStatus.NO_CONTENT.value(), "No Content", null);
        //        Delivery = deliveryService.index();
        //        return (main != null) ?
        //                ResponseEntity.status(HttpStatus.OK).body(main) :
        //                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 정렬
    @PostMapping("/sort")
    public Response show(@RequestBody DeliveryForm dto){
        return(deliveryService.show(dto)!= null) ?
                new Response(true, HttpStatus.OK.value(), "이용 내역 조회 완료", deliveryService.show(dto)) :
                new Response (false, HttpStatus.BAD_REQUEST.value(), "No Content", null);
    }

    // 작성
    @PostMapping("/add")
    public Response create(@RequestBody DeliveryForm dto){
        Delivery created = deliveryService.create(dto);
        if(created == null)
            return new Response(false, HttpStatus.BAD_REQUEST.value(), "추가 실패", null);
        if(created.getArticleId() == -1)
            return new Response(false, 301, "출발장소 입력은 필수야.", created.getArticleId());
        if(created.getArticleId() == -2)
            return new Response(false, 302, "도착장소 입력은 필수야.", created.getArticleId());
        if(created.getArticleId() == -3)
            return new Response(false, 303, "출발시간 입력은 필수야.", created.getArticleId());
        if(created.getArticleId() == -4)
            return new Response(false, 303, "도착시간 입력은 필수야.", created.getArticleId());
        if(created.getArticleId() == -5)
            return new Response(false, 304, "메뉴 입력은 필수야.", created.getArticleId());
        if(created.getArticleId() == -6)
            return new Response(false, 305, "배달팁 입력은 필수야.", created.getArticleId());
        return new Response(true, HttpStatus.CREATED.value(), "게시글이 작성되었습니다.", created.getArticleId());
    }

    // 수정
    @PutMapping("/update")
    public Response update(@RequestBody DeliveryForm dto){
        Delivery updated = deliveryService.update(dto);
        if(updated == null)
            return new Response(false, HttpStatus.BAD_REQUEST.value(), "추가 실패", null);
        if(updated.getArticleId() == -1)
            return new Response(false, 301, "출발장소 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -2)
            return new Response(false, 302, "도착장소 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -3)
            return new Response(false, 303, "출발시간 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -4)
            return new Response(false, 303, "도착시간 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -5)
            return new Response(false, 304, "메뉴 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -6)
            return new Response(false, 305, "배달팁 입력은 필수야.", updated.getArticleId());
        if(updated.getArticleId() == -7)
            return new Response(false, 308, "userId 불일치", updated.getArticleId());
        return new Response(true, HttpStatus.CREATED.value(), "수정되었습니다.", updated.getArticleId());
    }

    // 삭제
    @DeleteMapping("/delete")
    public Response delete(@RequestBody DeliveryForm dto){
        Delivery deleted = deliveryService.delete(dto);
        if(deleted == null)
            return new Response(false, 301, "이미 삭제된 게시글이거나 없는 게시글입니다.", null);
        if(deleted.getArticleId() == -1)
            return new Response(false, HttpStatus.BAD_REQUEST.value(), "삭제 실패 (접근 오류)",
                    deleted.getArticleId());
        if(deleted.getArticleId() == -2)
            return new Response(false, HttpStatus.UNAUTHORIZED.value(), "본인 계정이 아닌 게시글은 삭제할 수 없습니다.",
                    deleted.getArticleId());
        return new Response(true, HttpStatus.OK.value(), "게시글이 삭제되었습니다.", deleted.getArticleId());

    }

    // 이용내역 확인하기
    @GetMapping("/{userId}")
    public Response userList(@PathVariable String userId){
        return(deliveryService.userList(userId)!= null) ?
                new Response(true, HttpStatus.OK.value(), "이용 내역 조회 완료", deliveryService.userList(userId)) :
                new Response (false, HttpStatus.BAD_REQUEST.value(), "No Content", null);

    }


    @PostMapping("/my-order")
    public Response myOrder(@RequestBody DeliveryForm dto){
        Collection<Delivery> order = deliveryService.order(dto);
        if(order == null) return new Response(false, HttpStatus.BAD_REQUEST.value(), "ID가 없거나, 주문 내역이 없음", null);
        else return new Response(true, HttpStatus.OK.value(),"진행중인 이용내역 조회", order);
    }

}
