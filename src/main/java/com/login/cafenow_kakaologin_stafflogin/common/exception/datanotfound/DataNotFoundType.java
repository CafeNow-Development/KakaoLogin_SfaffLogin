package com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound;

public enum DataNotFoundType {

    DEFAULT("필수"),
    회원_못찾음("회원을 찾을 수 없습니다."),
    스텝_못찾음("스텝을 찾을 수 없습니다.")
    ;


    private final String message;

    DataNotFoundType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message.concat(" 데이터가 존재하지 않습니다.");
    }

    public String message(){
        return message;
    }
}
