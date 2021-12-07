package com.login.cafenow_kakaologin_stafflogin.security.authectication;

import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundType;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository.KakaoLoginRepository;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final KakaoLoginRepository kakaoLoginRepository;
    private final StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return kakaoLoginRepository.findByAdminEmail(email).orElseThrow(() -> new DataNotFoundException(DataNotFoundType.회원_못찾음));
    }

    public UserDetails CustomLoadUserByUsername(String email) throws UsernameNotFoundException {
        return staffRepository.findByStaffEmail(email).orElseThrow(() -> new DataNotFoundException(DataNotFoundType.스텝_못찾음));
    }
}
