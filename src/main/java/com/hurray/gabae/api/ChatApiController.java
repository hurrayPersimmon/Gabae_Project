package com.hurray.gabae.api;


import com.hurray.gabae.dto.ChatMessageDto;
import com.hurray.gabae.entity.Chat;
import com.hurray.gabae.entity.Delivery;
import com.hurray.gabae.entity.UserEntity;
import com.hurray.gabae.repository.ChatRoomRepository;
import com.hurray.gabae.repository.DeliveryRepository;
import com.hurray.gabae.repository.UserRepository;
import com.hurray.gabae.response.ChatRoomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatApiController {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    private final DeliveryRepository deliveryRepository;

    public ChatApiController(ChatRoomRepository chatRoomRepository, UserRepository userRepository, SimpMessageSendingOperations messagingTemplate, DeliveryRepository deliveryRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
        this.deliveryRepository = deliveryRepository;
    }

    @PostMapping("/rooms")
    public List<Chat> roomInfo(String userId){
        return chatRoomRepository.findRoomByUserId(userId);
    }

    //입장 했을 때 주는 메세지 - publisher 구현
    @MessageMapping("/message")
    public void message(ChatMessageDto message){
        if(ChatMessageDto.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @PostMapping("/make-room")
    public ChatRoomResponse createRoom(String myUserId, String otherUserId, Long articleId){
        // 왜 여기서 requestBody나 requestParam을 쓰지 않는지에 대해서 공부해야 할 것 같음
        // 상대방의 유저 정보 담기
        UserEntity other = userRepository.findByUserId(otherUserId).orElse(null);
        if(other == null) return new ChatRoomResponse(null,null,null
                ,null,null,false, HttpStatus.FORBIDDEN.value(), "룸 ID 반환 실패 (otherUserId가 등록되지 않음)");
        Chat foundRoom = chatRoomRepository.findRoom(myUserId, otherUserId);
        if(foundRoom == null){
            //true 방 없어서 방 생성
            Delivery senderUser = deliveryRepository.findByArticleId(articleId);
            String senderId , receiverId;
            if(senderUser.getUserId().equals(myUserId)){
                senderId = myUserId;
                receiverId = otherUserId;
            }
            else {
                senderId = otherUserId;
                receiverId = myUserId;
            }
            String postRoomId = chatRoomRepository.save(chatRoomRepository.createChatRoom(senderId, receiverId));

            return new ChatRoomResponse(postRoomId, other.getNickname(), other.getIntroduce(), other.getOrderRate(),
                    other.getDeliveryRate(),true, HttpStatus.OK.value(), "룸 ID 반환 성공");
        }
        //false 방 있으니깐 방 알려주기
        else return new ChatRoomResponse(foundRoom.getRoomId(), other.getNickname(), other.getIntroduce(), other.getOrderRate(),
                other.getDeliveryRate(),true, HttpStatus.OK.value(), "룸 ID 반환 성공");

    }



}
