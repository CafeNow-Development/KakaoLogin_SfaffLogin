package com.login.cafenow_kakaologin_stafflogin.common.response.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}