package com.devband.opencomm.support.oauth.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {

    private String email;

    private String nickname;
}
