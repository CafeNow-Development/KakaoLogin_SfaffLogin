package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.controller;

import com.login.cafenow_kakaologin_stafflogin.common.response.domain.SingleResult;
import com.login.cafenow_kakaologin_stafflogin.common.response.service.ResponseService;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.KakaoProfile;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository.KakaoLoginRepository;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.service.KakaoLoginService;
import com.login.cafenow_kakaologin_stafflogin.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"1. Kakao Login"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class KakaoLoginController {

    private final ResponseService responseService;
    private final KakaoLoginService kakaoLoginService;

    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/login/{provider}")
    public SingleResult<LoginResDto> signinByProvider(
            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
            @Valid @RequestBody LoginReqDto loginReqDto) {
        return responseService.getSingleResult(kakaoLoginService.getKakaoProfile(provider, loginReqDto));
    }
}
