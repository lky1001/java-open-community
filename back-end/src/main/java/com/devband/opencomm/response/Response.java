package com.devband.opencomm.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Response {

    private int code;
    private String msg;
}
