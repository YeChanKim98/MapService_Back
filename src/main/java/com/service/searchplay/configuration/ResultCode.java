package com.service.searchplay.configuration;

public enum ResultCode {

    CERTIFIED_FAIL{
        @Override
        public String toString(){
            return "CERTIFIED_FAIL";
        }
    }, //작성시 인증 실패
    WRITE_SUCCESS, //작성 성공
    WRITE_FAIL, //작성 실패
    DELETE_SUCCESS, //삭제 성공
    DELETE_FAIL, //삭제 실패
    UPDATE_SUCCESS, //수정 성공
    UPDATE_FAIL; //수정 실패

}
