package com.login.cafenow_kakaologin_stafflogin.common.model;

public interface EnumModel {

    default EnumValue getEnumValue(){
        return new EnumValue(getKey(), getValue());
    }

    String getKey();

    String getValue();
}