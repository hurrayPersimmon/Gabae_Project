package com.hurray.gabae.repository;

import com.hurray.gabae.dto.ChatRoomDto;
import com.hurray.gabae.entity.Chat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.hurray.gabae.entity.QChat.chat;

@Repository
public class ChatRoomRepository {
    private final JPAQueryFactory queryFactory;
    public ChatRoomRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    private Map<String, ChatRoomDto> chatRoomMap;



    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoomDto findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoomDto createChatRoom(String senderId, String receiverId) {
        ChatRoomDto chatRoom = ChatRoomDto.create(senderId, receiverId);
        //채팅방 생성하는거 만들어야 함. 아래 put메소드
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public List<Chat> findRoomByUserId(String userId){
        return queryFactory
                .selectFrom(chat)
                .where((chat.senderId.eq(userId).or(chat.receiverId.eq(userId))))
                .fetch();
    }

    public Chat findRoom(String senderId, String receiverId){
        return (Chat) queryFactory
                .selectFrom(chat)
                .where(chat.senderId.eq(senderId), chat.receiverId.eq(receiverId))
                .fetch();
    }

    public String save(ChatRoomDto chatRoom) {
                queryFactory
                .insert(chat)
                .columns(chat.roomId, chat.receiverId, chat.senderId)
                .values(chatRoom.getRoomId(), chatRoom.getReceiverId(), chatRoom.getSenderId());
                return chatRoom.getRoomId();
    }
}