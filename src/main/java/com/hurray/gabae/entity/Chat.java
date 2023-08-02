package com.hurray.gabae.entity;

import com.hurray.gabae.dto.ChatMessageDto;
import com.hurray.gabae.dto.ChatRoomDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@ToString
@NoArgsConstructor(force=true)
@Entity
@Getter
@Setter
public class Chat {
    private String userId;
    private String senderId;
    private String receiverId;
    public enum MessageType {
        ENTER, TALK
    }
    private ChatMessageDto.MessageType type; //메세지 타입
    @Id
    private String roomId; //방 번호
    private String sender;
    private String message;
    private String name;

//    public static ChatRoomDto create(String name) {
//        ChatRoomDto chatRoom = new ChatRoomDto();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }

}
