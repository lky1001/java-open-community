package com.devband.opencomm.security;

public enum Role {

    ROLE_USER("일반유저"),
    ;

    private final String desc;

    Role(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
