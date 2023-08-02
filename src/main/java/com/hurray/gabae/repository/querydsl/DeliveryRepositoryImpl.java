package com.hurray.gabae.repository.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hurray.gabae.entity.Delivery;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

import static com.hurray.gabae.entity.QDelivery.delivery;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Collection<Delivery> sorting(String startingPoint, String arrivingPoint,
                                        String searchDeliTime, String deliTip){
        return queryFactory
                .selectFrom(delivery)
                .where(startingPointEq(startingPoint),
                        arrivingPointEq(arrivingPoint),
                        setBySearchDeliTime(searchDeliTime))
                .orderBy(sortByDeliTip(deliTip))
                .fetch();
    }
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    LocalTime now = LocalTime.parse(LocalTime.now()
            .format(DateTimeFormatter.ofPattern("HH:mm")));

    public Collection<Delivery> findAllByNowTime(){
        return queryFactory
                .selectFrom(delivery)
                .where(delivery.endDeliTime.goe(now))
//                        ,delivery.startDeliTime.loe(now))
                .fetch();
    }

    public Collection<Delivery> findMyOrder(String userId){
        return queryFactory
                .selectFrom(delivery)
                .where(delivery.userId.eq(userId),
                        delivery.endDeliTime.goe(now))
//                        delivery.startDeliTime.loe(now))
                .fetch();
    }

    public BooleanExpression startingPointEq(String startingPoint){
        return !startingPoint.equals("") ? delivery.startingPoint.eq(startingPoint) : null;
    }

    public BooleanExpression arrivingPointEq(String arrivingPoint){
        return !arrivingPoint.equals("") ? delivery.arrivingPoint.eq(arrivingPoint) : null;
    }

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    LocalTime comparisonTime;
    public BooleanExpression setBySearchDeliTime(String searchDeliTime){
        if(searchDeliTime.isEmpty()) return delivery.endDeliTime.goe(now);
        else {
            comparisonTime = LocalTime.parse(searchDeliTime);
            return delivery.endDeliTime.goe(comparisonTime).
                    and(delivery.startDeliTime.loe(comparisonTime));
        }
    }

    private OrderSpecifier<?> sortByDeliTip(final String DeliTip) {
        //명시적으로 기록하기위해 isNull 사용
        if (Objects.isNull(DeliTip)) {
            return OrderByNull.getDefault();
        }

        if (DeliTip.equals("desc")) {
            return new OrderSpecifier<>(Order.DESC, delivery.deliTip);
        }
        if (DeliTip.equals("asc")) {
            return new OrderSpecifier<>(Order.ASC, delivery.deliTip);
        }
        return OrderByNull.getDefault();
    }

//    public BooleanExpression deliTipSort(String deliTip){
//        if (deliTip.equals("desc")) return delivery.deliTip.desc();
//        if (deliTip.equals("asc")) return delivery.deliTip.asc();
//        else return null;
//    }


}
