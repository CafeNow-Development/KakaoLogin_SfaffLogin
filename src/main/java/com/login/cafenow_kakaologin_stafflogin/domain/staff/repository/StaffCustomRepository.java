package com.login.cafenow_kakaologin_stafflogin.domain.staff.repository;


import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StaffCustomRepository {
    Optional<Staff> findByStaffEmail(String email);
}
