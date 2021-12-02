package com.login.cafenow_kakaologin_stafflogin.security.authectication;

import com.login.cafenow_kakaologin_stafflogin.common.exception.DataNotFoundException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.DataNotFoundType;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository.KakaoLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final KakaoLoginRepository kakaoLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return kakaoLoginRepository.findByEmail(username).orElseThrow(() -> new DataNotFoundException(DataNotFoundType.회원_못찾음));
    }
}
