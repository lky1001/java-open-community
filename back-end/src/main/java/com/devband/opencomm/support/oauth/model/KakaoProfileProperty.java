package com.devband.opencomm.support.oauth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfileProperty {

    private String nickname;

    @JsonProperty("thumbnail_image")
    private String thumbnailImage;

    @JsonProperty("profile_image")
    private String profileImage;
}
