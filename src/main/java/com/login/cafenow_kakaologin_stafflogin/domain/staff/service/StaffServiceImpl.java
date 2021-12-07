package com.login.cafenow_kakaologin_stafflogin.domain.staff.service;

import com.login.cafenow_kakaologin_stafflogin.common.util.CurrentAdminUtil;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffFindResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffSaveReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final CurrentAdminUtil currentAdminUtil;

    @Transactional
    @Override
    public void saveStaff(StaffSaveReqDto staffSaveReqDto) {
        Admin currentAdmin = currentAdminUtil.getCurrentAdmin();
        // 카프카로 Store 큐를 사용해서 가져오기
    }

    @Override
    public StaffLoginResDto loginStaff(StaffLoginReqDto staffLoginReqDto) {
        return null;
    }

    @Override
    public void deleteStaff(Long idx) {

    }

    @Override
    public List<StaffFindResDto> findAll() {
        return null;
    }

    @Override
    public StaffFindResDto findByIdx(Long idx) {
        return null;
    }
}
