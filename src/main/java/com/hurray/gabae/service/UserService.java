package com.hurray.gabae.service;

import com.hurray.gabae.dto.UserForm;
import com.hurray.gabae.entity.UserEntity;
import com.hurray.gabae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 로그인 구현
    public UserEntity register(UserForm dto) {
        UserEntity user = dto.toEntity();
        //예외 처리 들어가야 함. getID 없는 경우~
        if(user.getUserId().equals("")){
            user.setNickname("ID empty");
            return user;
        }
        if(user.getUserPw().equals("")){
            user.setNickname("PW empty");
            return user;
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://smart.gachon.ac.kr:8080/WebJSON";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        org.json.JSONObject jsonObject= new org.json.JSONObject();

        jsonObject.put("fsp_cmd","login");
        jsonObject.put("DVIC_ID","dwFraM1pVhl6mMn4npgL2dtZw7pZxw2lo2uqpm1yuMs=");
        jsonObject.put("fsp_action","UserAction");
        jsonObject.put("USER_ID",user.getUserId()); //user ID 사용자에게 받은 값 입력
        log.info(user.getUserId());
        jsonObject.put("PWD",user.getUserPw()); //user PW 사용자에게 받은 값 입력
        jsonObject.put("APPS_ID","com.sz.Atwee.gachon");

        HttpEntity<String> requestMessage = new HttpEntity<>(jsonObject.toString(), httpHeaders); //Request&Header Setting
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class); //호출 하여 Response 받기

        JSONObject data = new JSONObject(response.getBody());

        //0이면 인증 완료
        String code = (String) data.get("ErrorCode");
        if(code.equals("0")) {
            if(userRepository.existsById(user.getUserId())){
                log.info("userid {}", user.getUserId());
                return userRepository.findByUserId(user.getUserId()).orElse(null);
            }
            else {
                user.setNickname("first");
                return user;
            }
        }
        else{
            user.setNickname("Wrong");
            return user; // 비밀번호 혹은 아이디가 일치하지 않는 경우
        }
    }

    // 회원 탈퇴
    public UserEntity withdraw(UserForm dto) {
        UserEntity user = dto.toEntity();
        //예외 처리 들어가야 함. getID 없는 경우~
        if(user.getUserId() == null){
            user.setNickname("ID empty");
            return user;
        }
        if(user.getUserPw() == null){
            user.setNickname("PW empty");
            return user;
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://smart.gachon.ac.kr:8080/WebJSON";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        org.json.JSONObject jsonObject = new org.json.JSONObject();

        jsonObject.put("fsp_cmd", "login");
        jsonObject.put("DVIC_ID", "dwFraM1pVhl6mMn4npgL2dtZw7pZxw2lo2uqpm1yuMs=");
        jsonObject.put("fsp_action", "UserAction");
        jsonObject.put("USER_ID", user.getUserId()); //user ID 사용자에게 받은 값 입력
        jsonObject.put("PWD", user.getUserPw()); //user PW 사용자에게 받은 값 입력
        jsonObject.put("APPS_ID", "com.sz.Atwee.gachon");

        HttpEntity<String> requestMessage = new HttpEntity<>(jsonObject.toString(), httpHeaders); //Request&Header Setting
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class); //호출 하여 Response 받기

        JSONObject data = new JSONObject(response.getBody());

        //0이면 인증 완료
        String code = (String) data.get("ErrorCode");
        if (code.equals("0")) {
            UserEntity target = userRepository.findById(user.getUserId()).orElse(null);
            userRepository.delete(target);
            return target;
        }
        else { // 비밀번호 혹은 아이디가 일치하지 않는 경우
            user.setNickname("Wrong");
            return user;
        }
    }


    // 프로필 보기
    public UserEntity showPage(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // 프로필 설정
    public UserEntity setPage(UserForm dto, MultipartFile photoFile)throws Exception{
        UserEntity user = dto.toEntity();
        if (user.getNickname().length() >8){
            user.setNickname("nickname over");
            return user;
        }
        if (user.getIntroduce().length() >20){
            user.setNickname("introduce over");
            return user;
        }
        if(photoFile != null) {
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + photoFile.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            photoFile.transferTo(saveFile);

            user.setPhotoName(fileName);
            user.setPhotoPath("/img/" + fileName);
        }
        else user.setPhotoPath(null);
        return userRepository.save(user);
    }

//    https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fimage&psig=AOvVaw1xQgpaAL4E_51a6o-JJCCb&ust=1677724085876000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCNDA7bLXuf0CFQAAAAAdAAAAABAE
//    http://localhost:8080/userPF_IMG.png

//    // 프로필 사진 전송
//    public UserEntity getPhoto(UserForm dto, MultipartFile file) throws Exception {
//        UserEntity user = dto.toEntity();
//        if(user.getUserFile() == null)
//        return null; // 기본 파일 받아서 경로 설정해야 함.
//        return user;
//    }


    // 프로필 변경
    public UserEntity updatePage(String userId, UserForm dto, MultipartFile photoFile)throws Exception {
        UserEntity user = dto.toEntity();

        // 접근 오류 userId랑 request랑 다를 때
        if(!userId.equals(user.getUserId())) {
            user.setNickname("Wrong Path");
            return user;
        }
        // 닉네임 길이 오류
        if (user.getNickname().length() >8){
            user.setNickname("nickname over");
            return user;
        }

        // 자기소개 길이 오류
        if (user.getIntroduce().length() >20){
            user.setNickname("introduce over");
            return user;
        }

        // repository 반환 실패 저장되어 있지 않은 userId
        UserEntity target = userRepository.findById(userId).orElse(null);
        if(target == null) {
            user.setNickname("ERROR");
            return user;
        }

        if(photoFile != null) {
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/img";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + photoFile.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            photoFile.transferTo(saveFile);

            user.setPhotoName(fileName);
            user.setPhotoPath("/img/" + fileName);
        }
        else user.setPhotoPath(null);

        target.setting(user);
        UserEntity pageUpdated = userRepository.save(target);
        return pageUpdated;
    }

    public UserEntity deliveryRate(UserForm dto) {
        UserEntity user = dto.toEntity();
        UserEntity target = userRepository.findById(user.getUserId()).orElse(null);
        if(target.getDeliveryNum() == 0){ // 나눌값이 0일 경우
            target.setDeliveryNum(1.0);
            target.setDeliveryRate(user.getDeliveryRate());
            UserEntity dRateUpdated = userRepository.save(target);
            return dRateUpdated;
        }
        else {
            target.setDeliveryNum( target.getDeliveryNum() +1 );
            target.setDeliveryRate((target.getDeliveryNum() * target.getDeliveryRate() + user.getDeliveryRate())
                    /target.getDeliveryNum());
            UserEntity dRateUpdated = userRepository.save(target);
            return dRateUpdated;
        }
    }

    public UserEntity orderRate(UserForm dto) {
        UserEntity user = dto.toEntity();
        UserEntity target = userRepository.findById(user.getUserId()).orElse(null);
        if(target.getOrderNum() == 0){ // 나눌값이 0일 경우
            target.setOrderNum(1.0);
            target.setOrderRate(user.getOrderRate());
            UserEntity oRateUpdated = userRepository.save(target);
            return oRateUpdated;
        }
        else {
            target.setOrderNum( target.getOrderNum() +1 );
            target.setOrderRate((target.getOrderNum() * target.getOrderRate() + target.getOrderRate())
                    /target.getOrderNum());
            UserEntity oRateUpdated = userRepository.save(target);
            return oRateUpdated;
        }

    }

}
