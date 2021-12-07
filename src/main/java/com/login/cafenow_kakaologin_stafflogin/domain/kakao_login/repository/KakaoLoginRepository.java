package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoLoginRepository extends JpaRepository<Admin, Long>, KakaoLoginCustomRepository {
}
