package com.hurray.gabae.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSuggestEntity is a Querydsl query type for SuggestEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSuggestEntity extends EntityPathBase<SuggestEntity> {

    private static final long serialVersionUID = 516443834L;

    public static final QSuggestEntity suggestEntity = new QSuggestEntity("suggestEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final StringPath suggestionContent = createString("suggestionContent");

    public final StringPath userId = createString("userId");

    public QSuggestEntity(String variable) {
        super(SuggestEntity.class, forVariable(variable));
    }

    public QSuggestEntity(Path<? extends SuggestEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSuggestEntity(PathMetadata metadata) {
        super(SuggestEntity.class, metadata);
    }

}

