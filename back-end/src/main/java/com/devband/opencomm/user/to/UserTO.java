package com.devband.opencomm.user.to;

import com.devband.opencomm.user.model.EnumUserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTO {

    private int id;
    private String userId;
    private String userPw;
    private String nickname;
    private EnumUserType type;
}
