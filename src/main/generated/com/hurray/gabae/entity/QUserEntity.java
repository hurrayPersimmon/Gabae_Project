package com.hurray.gabae.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -2089608645L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final NumberPath<Double> deliveryNum = createNumber("deliveryNum", Double.class);

    public final NumberPath<Double> deliveryRate = createNumber("deliveryRate", Double.class);

    public final StringPath introduce = createString("introduce");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Double> orderNum = createNumber("orderNum", Double.class);

    public final NumberPath<Double> orderRate = createNumber("orderRate", Double.class);

    public final StringPath photoName = createString("photoName");

    public final StringPath photoPath = createString("photoPath");

    public final StringPath userId = createString("userId");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

