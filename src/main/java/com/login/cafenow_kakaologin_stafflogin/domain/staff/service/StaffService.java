package com.login.cafenow_kakaologin_stafflogin.domain.staff.service;

import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffFindResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffSaveReqDto;

import java.util.List;

public interface StaffService {

    // 회원가입 (저장)
    void saveStaff(StaffSaveReqDto staffSaveReqDto);

    // 로그인
    StaffLoginResDto loginStaff(StaffLoginReqDto staffLoginReqDto);

    // 삭제
    void deleteStaff(Long idx);

    // 전제 조회
    List<StaffFindResDto> findAll();

    // 단일 조회
    StaffFindResDto findByIdx(Long idx);

}
