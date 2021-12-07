package com.login.cafenow_kakaologin_stafflogin.domain.staff.repository;

import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>, StaffCustomRepository {

}
