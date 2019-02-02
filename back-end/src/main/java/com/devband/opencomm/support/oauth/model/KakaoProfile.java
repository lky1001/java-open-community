package com.devband.opencomm.support.oauth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoProfile {

    private String id;

    private KakaoProfileProperty properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}
