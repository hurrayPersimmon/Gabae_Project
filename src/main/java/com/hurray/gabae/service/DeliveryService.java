package com.hurray.gabae.service;

import com.hurray.gabae.dto.DeliveryForm;
import com.hurray.gabae.entity.Delivery;
import com.hurray.gabae.repository.DeliveryRepository;
import com.hurray.gabae.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class DeliveryService {
    @Autowired //DI 의존성 주입.
    private DeliveryRepository deliveryRepository;

    // 기본 페이지
    public List<Delivery> index() {
        List<Delivery> listdata = deliveryRepository.findAllByNowTime();
        return listdata;
    }

    // 검색 메소드 구현
    public Collection<Delivery> show(DeliveryForm dto) {
        Delivery sorting = dto.toEntity();
        Collection<Delivery> sortingData = deliveryRepository.sorting(
                sorting.getStartingPoint(),
                sorting.getArrivingPoint(),
                sorting.getSearchDeliTime(),
                sorting.getDeliTip()
                );
        return sortingData;
    }

    // 작성
    public Delivery create(DeliveryForm dto) {
        Delivery delivery = dto.toEntity();
        if(delivery.getUserId() == null) {
            log.info("ERROR! userID null");
            return null;
        }
        if(delivery.getStartingPoint().equals("출발 장소")){
            log.info("ERROR! startingPoint null!");
            delivery.setArticleId((long) -1);
            return delivery;
        }
        if(delivery.getArrivingPoint().equals("도착 장소")){
            log.info("ERROR! arrivingPoint null!");
            delivery.setArticleId((long) -2);
            return delivery;
        }
        if(delivery.getStartDeliTime() == null){
            log.info("ERROR! startDeliTime null!");
            delivery.setArticleId((long) -3);
            return delivery;
        }
        if(delivery.getEndDeliTime() == null){
            log.info("ERROR! endDeliTime null!");
            delivery.setArticleId((long) -4);
            return delivery;
        }
        if(delivery.getMenu().equals("메뉴")){
            log.info("ERROR! menu null!");
            delivery.setArticleId((long) -5);
            return delivery;
        }
        if(delivery.getDeliTip().equals("배달팁")){
            log.info("ERROR! deliTip null!");
            delivery.setArticleId((long) -6);
            return delivery;
        }
        return deliveryRepository.save(delivery);
    }

    // 수정
    public Delivery update(DeliveryForm dto) {
        Delivery delivery = dto.toEntity();
        log.info("articleId : {}, data : {}", delivery.getArticleId(), delivery.toString());

        Delivery target = deliveryRepository.findById(delivery.getArticleId()).orElse(null);
        if(!target.getUserId().equals(delivery.getUserId())) { // 아이디가 다른 경우 (본인의 글 아닐때.
            log.info("wrong request! article userId = {}, req userId = {}", delivery.getUserId(), target.getUserId());
            target.setArticleId((long) -7);
            return target;
        }
        if(delivery.getStartingPoint().equals("출발 장소")){
            log.info("ERROR! startingPoint null!");
            delivery.setArticleId((long) -1);
            return delivery;
        }
        if(delivery.getArrivingPoint().equals("도착 장소")){
            log.info("ERROR! arrivingPoint null!");
            delivery.setArticleId((long) -2);
            return delivery;
        }
        if(delivery.getStartDeliTime() == null){
            log.info("ERROR! startDeliTime null!");
            delivery.setArticleId((long) -3);
            return delivery;
        }
        if(delivery.getEndDeliTime() == null){
            log.info("ERROR! endDeliTime null!");
            delivery.setArticleId((long) -4);
            return delivery;
        }
        if(delivery.getMenu().equals("메뉴")){
            log.info("ERROR! menu null!");
            delivery.setArticleId((long) -5);
            return delivery;
        }
        if(delivery.getDeliTip().equals("배달팁")){
            log.info("ERROR! deliTip null!");
            delivery.setArticleId((long) -6);
            return delivery;
        }
        // 업데이트
        target.putting(delivery);
        Delivery updated = deliveryRepository.save(target);
        return updated;
    }

    // 삭제
    public Delivery delete(DeliveryForm dto) {
        Delivery delivery = dto.toEntity();
        Delivery target = deliveryRepository.findById(delivery.getArticleId()).orElse(null);

        if(target == null) return null;

        if(!target.getArticleId().equals(delivery.getArticleId())){
            log.info("ERROR! articleId 다름");
            delivery.setArticleId((long) -1);
            return delivery;
        }

        if(!target.getUserId().equals(delivery.getUserId())) {
            log.info("ERROR! ID 다름");
            delivery.setArticleId((long) -2);
            return delivery;
        }

        deliveryRepository.delete(target);
        return target;
    }

    // 이용 내역 확인
    public Collection<Delivery> userList(String userId) {
        Collection<Delivery> userList = deliveryRepository.findByUserId(userId);
        return userList;
    }

    // 진행중인 이용내역 조회
    public Collection<Delivery> order(DeliveryForm dto) {
        Delivery delivery = dto.toEntity();
        if(delivery.getUserId() == null) return null;
        Collection<Delivery> order = deliveryRepository
                .findMyOrder(delivery.getUserId());
        return order;
    }


}
