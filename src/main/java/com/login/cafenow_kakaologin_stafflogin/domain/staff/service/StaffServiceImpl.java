package com.login.cafenow_kakaologin_stafflogin.domain.staff.service;

import com.login.cafenow_kakaologin_stafflogin.common.exception.badrequest.BadRequestException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.badrequest.BadRequestType;
import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundType;
import com.login.cafenow_kakaologin_stafflogin.common.util.CurrentAdminUtil;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffFindResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffLoginResDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.dto.StaffSaveReqDto;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.repository.StaffRepository;
import com.login.cafenow_kakaologin_stafflogin.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final StaffRepository staffRepository;
    private final CurrentAdminUtil currentAdminUtil;
    private final ModelMapper mapper;

    @Transactional
    @Override
    public void saveStaff(StaffSaveReqDto staffSaveReqDto) {
        Admin currentAdmin = currentAdminUtil.getCurrentAdmin();
        // 카프카로 Store 큐를 사용해서
    }

    @Override
    public StaffLoginResDto loginStaff(StaffLoginReqDto staffLoginReqDto) {
        Staff staff = staffRepository.findByStaffEmail(staffLoginReqDto.getStaffEmail()).orElseThrow(() -> new BadRequestException(BadRequestType.WRONG_EMAIL));
        boolean check = passwordEncoder.matches(staffLoginReqDto.getStaffPassword(), staff.getStaffPassword());
        if(!check) {
            throw new BadRequestException(BadRequestType.WRONG_PASSWORD);
        }
        StaffLoginResDto staffLoginResDto = StaffLoginResDto.builder()
                    .staffEmail(staff.getStaffEmail())
                    .staffName(staff.getStaffName())
                    .accessToken(jwtTokenProvider.createTokenStaff(staff))
                    .role(staff.getRole())
                    .build();

        return staffLoginResDto;
    }

    @Override
    public void deleteStaff(Long idx) {
        staffRepository.deleteById(idx);
    }

    @Override
    public List<StaffFindResDto> findAll() {
        return staffRepository.findAll().stream()
                .map(m -> mapper.map(m, StaffFindResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StaffFindResDto findByIdx(Long idx) {
        return staffRepository.findById(idx)
                .map(m -> mapper.map(m, StaffFindResDto.class))
                .orElseThrow(() -> new DataNotFoundException(DataNotFoundType.스텝_못찾음));
    }
}
