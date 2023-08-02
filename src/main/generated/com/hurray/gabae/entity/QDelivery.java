package com.hurray.gabae.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = -838040095L;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath arrivingPoint = createString("arrivingPoint");

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDt = _super.createDt;

    public final StringPath deliTip = createString("deliTip");

    public final TimePath<java.time.LocalTime> endDeliTime = createTime("endDeliTime", java.time.LocalTime.class);

    public final StringPath menu = createString("menu");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final BooleanPath proceeding = createBoolean("proceeding");

    public final TimePath<java.time.LocalTime> startDeliTime = createTime("startDeliTime", java.time.LocalTime.class);

    public final StringPath startingPoint = createString("startingPoint");

    public final StringPath userId = createString("userId");

    public final StringPath userWant = createString("userWant");

    public QDelivery(String variable) {
        super(Delivery.class, forVariable(variable));
    }

    public QDelivery(Path<? extends Delivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDelivery(PathMetadata metadata) {
        super(Delivery.class, metadata);
    }

}

