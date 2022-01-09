package com.login.cafenow_kakaologin_stafflogin.domain.staff.dto;

import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.Admin;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.enumType.AdminRoleType;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffSaveReqDto {

    @NotBlank(message = "스탭 이메일을 입력해주세요.")
    private String staffEmail;

    @NotBlank(message = "스탭 비밀번호를 입력해주세요.")
    private String staffPassword;

    @NotBlank(message = "스탭 이름을 입력해주세요.")
    private String staffName;

    @NotBlank(message = "스탭 전화번호를 입력해주세요.")
    private String staffPhoneNumber;

    public Staff saveStaff(Admin admin) {
        return Staff.builder()
                .staffEmail(this.staffEmail)
                .staffPassword(this.staffPassword)
                .staffName(this.staffName)
                .staffPhoneNumber(this.staffPhoneNumber)
                .admin(admin)
                .roles(Collections.singletonList(AdminRoleType.ROLE_STAFF))
                .build();
    }
}
