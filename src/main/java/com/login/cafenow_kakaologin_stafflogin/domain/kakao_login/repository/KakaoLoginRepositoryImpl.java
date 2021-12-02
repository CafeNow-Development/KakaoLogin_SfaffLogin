package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.QAdmin;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.QAdmin.admin;

@RequiredArgsConstructor
public class KakaoLoginRepositoryImpl implements KakaoLoginRepository {

    private final JPQLQueryFactory queryFactory;

    @Override
    public Optional<Admin> findByEmail(String email) {
        return Optional.ofNullable(
                queryFactory.selectFrom(admin)
                        .where(admin.email.eq(email))
                        .fetchOne()
        );
    }
}
