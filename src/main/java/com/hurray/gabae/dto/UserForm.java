package com.hurray.gabae.dto;


import com.hurray.gabae.entity.UserEntity;
import lombok.*;

import java.io.File;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
@Getter
public class UserForm {

    private String userId;
    private String userPw;
    private Double deliveryRate;
    private Double deliveryNum;
    private Double orderRate;
    private Double orderNum;
    private String nickname;
    private String introduce;
    private String photoPath;
    private String photoName;
    public UserEntity toEntity() { return new UserEntity(userId, userPw,
            deliveryRate, deliveryNum, orderRate, orderNum,
            nickname, introduce, photoPath, photoName);  }


}
