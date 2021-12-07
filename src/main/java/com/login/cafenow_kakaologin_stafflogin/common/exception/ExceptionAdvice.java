package com.login.cafenow_kakaologin_stafflogin.common.exception;

import com.login.cafenow_kakaologin_stafflogin.common.exception.badrequest.BadRequestException;
import com.login.cafenow_kakaologin_stafflogin.common.exception.datanotfound.DataNotFoundException;
import com.login.cafenow_kakaologin_stafflogin.common.response.domain.CommonResult;
import com.login.cafenow_kakaologin_stafflogin.common.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(DataNotFoundException.class)
    protected CommonResult DataNotFoundException(DataNotFoundException e) {
        return responseService.getFailResult(-1, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    protected CommonResult BadRequestException(BadRequestException e) {
        return responseService.getFailResult(-1, e.getMessage());
    }
}
