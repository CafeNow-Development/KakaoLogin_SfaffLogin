package com.login.cafenow_kakaologin_stafflogin.domain.staff.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.login.cafenow_kakaologin_stafflogin.domain.staff.model.QStaff.staff;

@RequiredArgsConstructor
public class StaffCustomRepositoryImpl implements StaffCustomRepository {

    private final JPQLQueryFactory queryFactory;

    @Override
    public Optional<Staff> findByStaffEmail(String email) {
        return Optional.ofNullable(
                queryFactory.selectFrom(staff)
                        .where(staff.staffEmail.eq(email))
                        .fetchOne()
        );
    }
}
