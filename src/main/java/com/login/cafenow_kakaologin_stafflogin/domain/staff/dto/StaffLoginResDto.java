package com.login.cafenow_kakaologin_stafflogin.domain.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffLoginResDto {

    private String staffEmail;
    private String staffName;
    private String accessToken;
    private String role;

}
