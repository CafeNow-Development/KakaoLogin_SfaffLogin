package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.QAdmin.admin;

@RequiredArgsConstructor
public class KakaoLoginCustomRepositoryImpl implements KakaoLoginCustomRepository {

    private final JPQLQueryFactory queryFactory;

    @Override
    public Optional<Admin> findByAdminEmail(String email) {
        return Optional.ofNullable(
                queryFactory.selectFrom(admin)
                        .where(admin.email.eq(email))
                        .fetchOne()
        );
    }

    @Override
    public Admin findByEmailAndProvider(String email, String provider) {
        return queryFactory.selectFrom(admin)
                .where(admin.provider.eq(provider).and(admin.email.eq(email)))
                .fetchOne();
    }
}
