package com.login.cafenow_kakaologin_stafflogin.common.util;

import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundType;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.repository.KakaoLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentAdminUtil {

    private final KakaoLoginRepository kakaoLoginRepository;

    public String getCurrentUsername() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else{
            username = principal.toString();
        }
        System.out.println("====================================================");
        System.out.println("username = " + username);
        return username;
    }

    public Admin getCurrentAdmin() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else{
            username = principal.toString();
        }
        return kakaoLoginRepository.findByAdminEmail(username).orElseThrow(() -> new DataNotFoundException(DataNotFoundType.회원_못찾음));
    }
}
