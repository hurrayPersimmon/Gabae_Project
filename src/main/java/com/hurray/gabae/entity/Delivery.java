package com.hurray.gabae.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;


@AllArgsConstructor
@ToString
@NoArgsConstructor(force=true)
@Entity
@Getter
@Setter
//@SequenceGenerator(
//        name = "ARTICLE_GENERATOR",
//        sequenceName = "ARTICLE_SEQ",
//        initialValue = 1,
//        allocationSize = 1)
public class Delivery extends BaseTimeEntity{
    //큰일났다. 복힙키는 @GeneratedValue를 사용할 수 없대
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_GENERATOR") // 자동생성 어노테이션 (1... 자동생성) + DB가 ID 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    //https://codingexplore.tistory.com/69 참조하면 uuid 생성하는 방법 나와있음.

//    @GeneratedValue(generator = "USER_GENERATOR")
//    @GenericGenerator(name ="USER_GENERATOR", strategy = "")// generationType 공부할 것. 기본키가 생성되어 있어야 한다.

//    @OneToMany(mappedBy = "UserEntity")
//    @JoinColumn
    private String userId; // 아이디
//    Delivery userId = new userId();
//    Delivery.setuserId("id1"); // 기본 키 직접 할당
//    em.persist(userId);

    private String startingPoint; // 출발지점
    private String arrivingPoint; // 도착지점
    private String menu; // 메뉴
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime startDeliTime; // 출발시간
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endDeliTime; // 도착시간
    private String deliTip; // 배달팁
    private String userWant; // 요청사항
    @Transient
    @JsonIgnore
    @DateTimeFormat(pattern = "HH:mm")
    private String searchDeliTime;

    @JsonIgnore
    private Boolean proceeding;


    public void putting(Delivery delivery){
        if(delivery.startingPoint != null) this.startingPoint = delivery.startingPoint;
        if(delivery.arrivingPoint != null) this.arrivingPoint = delivery.arrivingPoint;
        if(delivery.menu != null) this.menu = delivery.menu;
        if(delivery.startDeliTime != null) this.startDeliTime = delivery.startDeliTime;
        if(delivery.endDeliTime != null) this.endDeliTime = delivery.endDeliTime;
        if(delivery.deliTip != null) this.deliTip = delivery.deliTip;
        if(delivery.userWant != null) this.userWant = delivery.userWant;
    }


}
// 복합키 관련
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class DeliveryId implements Serializable {
//    @EqualsAndHashCode.Include
//    public Long articleId;
//    @EqualsAndHashCode.Include
//    public String userId;
//}

