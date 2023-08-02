package com.hurray.gabae.repository.querydsl;

import com.hurray.gabae.dto.DeliveryForm;
import com.hurray.gabae.entity.Delivery;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Collection;

@Repository
public interface DeliveryRepositoryCustom{
    Collection<Delivery> sorting(String startingPoint, String arrivingPoint,
                                 String searchDeliTime, String deliTip);
}