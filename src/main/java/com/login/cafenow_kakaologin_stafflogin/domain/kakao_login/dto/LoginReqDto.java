package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.dto;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.enumType.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank(message = "Kakao에서 발급 받은 토큰을 입력해주세요.")
    private String accessToken;

    public Admin saveAdmin(KakaoProfile profile, String provider) {
        return Admin.builder()
                .email(profile.getEmail())
                .provider(provider)
                .name(profile.getNickName())
                .roles(Collections.singletonList(AdminRole.ROLE_ADMIN))
                .profile_image_url(profile.getProfile_image_url())
                .thumbnail_image_url(profile.getThumbnail_image_url())
                .is_email_valid(profile.getIs_email_valid())
                .is_email_verified(profile.getIs_email_verified())
                .build();
    }
}