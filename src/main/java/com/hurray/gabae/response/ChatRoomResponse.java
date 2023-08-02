package com.hurray.gabae.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomResponse<T> {
    private String RoomId;
    private String nickname;
    private String introduce;
    private Double orderRate;
    private Double deliveryRate;
    private boolean success;
    private int property;
    private String message;


}
