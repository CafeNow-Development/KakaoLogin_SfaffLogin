package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.service;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginResDto;

public interface KakaoLoginService {
    LoginResDto getKakaoProfile(String provider, LoginReqDto loginReqDto);
}
