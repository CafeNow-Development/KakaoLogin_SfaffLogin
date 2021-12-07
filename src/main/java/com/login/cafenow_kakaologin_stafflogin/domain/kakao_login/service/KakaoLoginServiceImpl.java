package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.service;

import com.google.gson.Gson;
import com.login.cafenow_kakaologin_stafflogin.common.exception.badrequest.BadRequestException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.badrequest.BadRequestType;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.KakaoProfile;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto.LoginResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository.KakaoLoginRepository;
import com.login.cafenow_kakaologin_stafflogin.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class KakaoLoginServiceImpl implements KakaoLoginService {

    private final KakaoLoginRepository kakaoLoginRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;

    @Override
    public LoginResDto getKakaoProfile(String provider, LoginReqDto loginReqDto) {
        HttpHeaders headers = getHttpHeaders();
        headers.set("Authorization", "Bearer " + loginReqDto.getAccessToken());

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try {
            // Request profile
            ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.profile"), request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                KakaoProfile kakaoProfile = gson.fromJson(response.getBody(), KakaoProfile.class);

                Admin admin = kakaoLoginRepository.findByEmailAndProvider(kakaoProfile.getEmail(), provider);
                if(admin == null) {
                    Admin saveAdmin = kakaoLoginRepository.save(loginReqDto.saveAdmin(kakaoProfile, provider));
                    LoginResDto login = LoginResDto.mapping(saveAdmin, provider, jwtTokenProvider.createTokenAdmin(saveAdmin));
                    return login;
                } else {
                    LoginResDto login = LoginResDto.mapping(admin, provider, jwtTokenProvider.createTokenAdmin(admin));
                    return login;
                }
            }
        } catch (Exception e) {
            throw new BadRequestException(BadRequestType.KAKAO_LOGIN_COMMUNICATION_FAIL);
        }
        throw null;
    }

    @NotNull
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
