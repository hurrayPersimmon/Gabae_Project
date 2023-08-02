package com.hurray.gabae.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoomDto {
    private String roomId;
    private String senderId;
    private String receiverId;

    public static ChatRoomDto create(String senderId, String receiverId) {
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.senderId = senderId;
        chatRoom.receiverId = receiverId;
        return chatRoom;
    }
}