package com.hurray.gabae.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 1259994053L;

    public static final QChat chat = new QChat("chat");

    public final StringPath message = createString("message");

    public final StringPath name = createString("name");

    public final StringPath receiverId = createString("receiverId");

    public final StringPath roomId = createString("roomId");

    public final StringPath sender = createString("sender");

    public final StringPath senderId = createString("senderId");

    public final EnumPath<com.hurray.gabae.dto.ChatMessageDto.MessageType> type = createEnum("type", com.hurray.gabae.dto.ChatMessageDto.MessageType.class);

    public final StringPath userId = createString("userId");

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    public QChat(Path<? extends Chat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata metadata) {
        super(Chat.class, metadata);
    }

}

