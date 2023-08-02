package com.hurray.gabae.api;


import com.hurray.gabae.dto.UserForm;
import com.hurray.gabae.entity.UserEntity;
import com.hurray.gabae.response.RegisterResponse;
import com.hurray.gabae.response.Response;
import com.hurray.gabae.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    // 로그인 구현
    @PostMapping("/user/register")
    public RegisterResponse register(@RequestBody UserForm dto){
        UserEntity registration = userService.register(dto);
        if(registration.getNickname().equals("ID empty"))
            return new RegisterResponse(false, 301 , "아이디를 입력해주세요", false);
        if(registration.getNickname().equals("PW empty"))
            return new RegisterResponse(false, 302 , "비밀번호를 입력해주세요", false);
        if(registration.getNickname().equals("Wrong"))
            return new RegisterResponse(false, HttpStatus.BAD_REQUEST.value(), "아이디 혹은 비밀번호가 일치하지 않습니다.", false);
        if(registration.getNickname().equals("first"))
            return new RegisterResponse(true, HttpStatus.OK.value(), "처음 접속하신 회원입니다.", false);
        else return new RegisterResponse(true, 202, registration.getNickname() + "님 환영합니다.", true);
    }

    // 회원 탈퇴 구현
    @DeleteMapping("/user/withdraw")
    public Response withdraw(@RequestBody UserForm dto){
        UserEntity withdrawal = userService.withdraw(dto);
        if(withdrawal.getNickname().equals("ID empty"))
            return new Response(false, 301 , "아이디를 입력해주세요", null);
        if(withdrawal.getNickname().equals("PW empty"))
            return new Response(false, 302 , "비밀번호를 입력해주세요", null);
        if(withdrawal.getNickname().equals("Wrong"))
            return new Response(false, HttpStatus.BAD_REQUEST.value(),"아이디 혹은 비밀번호가 일치하지 않습니다." ,null);
        else return new Response(true, HttpStatus.OK.value(), "회원 탈퇴 완료", withdrawal.getUserId());
    }

    // 프로필 보기

    @GetMapping("/user/{userId}")
    public Response showPage(@PathVariable String userId){
        UserEntity shown = userService.showPage(userId);
        return (shown != null) ?
                new Response(true, HttpStatus.OK.value(), "프로필 조회 완료", shown) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"No Content" ,null);

    }

    // 프로필 설정
    @PostMapping("/user/setting")
    public Response setPage(@RequestBody UserForm dto, @RequestPart MultipartFile photoFile) throws Exception{
        UserEntity setting = userService.setPage(dto, photoFile);
        if(setting.getNickname().equals("nickname over"))
            return new Response(false, 301, "닉네임은 8자가 최대입니다.", null);
        if(setting.getNickname().equals("introduce over"))
            return new Response(false, 302, "소개 말은 20자가 최대입니다.", null);

        return (setting != null) ?
                new Response(true, HttpStatus.OK.value(), "프로필 설정 완료", setting) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"프로필 설정 오류" ,null);

    }

//    //프로필 사진 전송
//    @PostMapping("/user/photo/get")
//    public Response getPhoto(@RequestBody UserForm dto, MultipartFile file) throws Exception{
//        UserEntity gettingPhoto = userService.getPhoto(dto, file);
//        return (gettingPhoto != null) ?
//                new Response(true, HttpStatus.OK.value(), "",gettingPhoto.getUserFile()) :
//                new Response(false, HttpStatus.BAD_REQUEST.value(), "", null);
//    }

    // 프로필 변경
    @PutMapping("/user/update/{userId}")
    public Response updatePage(@PathVariable String userId, @RequestBody UserForm dto, MultipartFile file)throws Exception{
        UserEntity updated = userService.updatePage(userId, dto, file);
        if(updated.getNickname().equals("nickname over"))
            return new Response(false, 301, "닉네임은 8자가 최대입니다.", null);
        if(updated.getNickname().equals("introduce over"))
            return new Response(false, 302, "소개 말은 20자가 최대입니다.", null);
        return (updated != null) ?
                new Response(true, HttpStatus.ACCEPTED.value(), "프로필 변경 완료", updated) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"프로필 변경 실패" ,null);

    }

    // 배달한 사람에게 별점 남기기
    @PutMapping("/user/deliveryRate")
    public Response deliveryRate(@RequestBody UserForm dto){
        UserEntity dRateUpdate = userService.deliveryRate(dto);
        return (dRateUpdate != null) ?
                new Response(true, HttpStatus.OK.value(), "배달한 사람에게 별점 부여 완료", dRateUpdate.getDeliveryRate()) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"" ,null);

    }

    // 배달받은 사람에게 별점 남기기
    @PutMapping("/user/orderRate")
    public Response orderRate(@RequestBody UserForm dto){
        UserEntity oRateUpdate = userService.orderRate(dto);
        return (oRateUpdate != null) ?
                new Response(true, HttpStatus.OK.value(), "배달 받은사람에게 별점 부여 완료", oRateUpdate.getOrderRate()) :
                new Response(false, HttpStatus.BAD_REQUEST.value(),"" ,null);

    }

}
