package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.enumType;

import com.login.cafenow_kakaologin_stafflogin.common.model.EnumModel;

public enum AdminRoleType implements EnumModel {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STAFF("ROLE_STAFF")
    ;

    private final String value;

    AdminRoleType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
