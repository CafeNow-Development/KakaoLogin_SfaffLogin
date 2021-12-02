package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaoLoginRepository {
    Optional<Admin> findByEmail(String email);
}
