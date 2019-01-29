package com.devband.opencomm.user.model;

public enum EnumUserType {

    EMAIL("이메일 가입"),
    KAKAO("카카오 가입"),
    ;

    private final String desc;

    EnumUserType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
