package com.hurray.gabae.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.File;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
//    @JoinColumn
    private String userId;
    @Transient
    @JsonIgnore
    private String userPw;

    // double은 primitive type (원시타입) 이므로 reference type (참조타입) Double로 변경
    // wrapper class 공부해서 정리할 것.
    private Double deliveryRate = 0.0;
    private Double deliveryNum = 0.0;
    private Double orderRate = 0.0;
    private Double orderNum= 0.0;
    private String nickname;
    private String introduce;
    private String photoPath;
    @JsonIgnore
    private String photoName;

    public void setting(UserEntity user) {
        if (user.nickname != null) this.nickname = nickname;
        if (user.introduce != null) this.introduce = introduce;
        if (user.photoName != null) this.photoName = photoName;
        if (user.photoPath != null) this.photoPath = photoPath;
    }
}