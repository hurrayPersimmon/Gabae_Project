package com.hurray.gabae.dto;


import com.hurray.gabae.entity.Delivery;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class DeliveryForm {
    private String userId;
    private String startingPoint;
    private String arrivingPoint;

    private LocalTime startDeliTime;
    private LocalTime endDeliTime;
    private String deliTip;
    private String userWant;
    private Long articleId;
    private String menu;
    private String searchDeliTime;
    private Boolean proceeding;

//    @QueryProjection
    public Delivery toEntity() { return new Delivery(articleId, userId, startingPoint, arrivingPoint, menu,
            startDeliTime, endDeliTime, deliTip, userWant, searchDeliTime, proceeding); }
}
