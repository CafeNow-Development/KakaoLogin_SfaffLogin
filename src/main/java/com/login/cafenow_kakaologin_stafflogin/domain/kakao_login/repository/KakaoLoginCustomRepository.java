package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;

import java.util.Optional;

public interface KakaoLoginCustomRepository {
    Optional<Admin> findByAdminEmail(String email);
    Admin findByEmailAndProvider(String email, String provider);
}
