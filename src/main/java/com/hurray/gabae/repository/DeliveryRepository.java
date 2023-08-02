package com.hurray.gabae.repository;

import com.hurray.gabae.entity.Delivery;
import com.hurray.gabae.entity.QDelivery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.repository.CrudRepository;
import com.hurray.gabae.repository.querydsl.DeliveryRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.hurray.gabae.entity.QDelivery.delivery;


public interface DeliveryRepository extends CrudRepository<Delivery, Long>, DeliveryRepositoryCustom{
    @Override
    ArrayList<Delivery> findAll();
    public Collection<Delivery> findByUserId(String userId);
    List<Delivery> findAllByNowTime();
    List<Delivery> findMyOrder(String userId);
    Delivery findByArticleId(Long articleId);
}
