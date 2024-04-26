package thecommerce.toy.global.error.enums;


import thecommerce.toy.global.exception.payload.response.ErrorResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum ErrorCode {

    //  === COMMON (0000) ============================================================================================================
    REQUEST_BINDING_RESULT("0001", "Request 바인딩 에러"),
    // === MEMBER (1000) ============================================================================================================
    ALREADY_REGISTERED_MEMBER_BY_LOGIN_ID("1001", "이미 등록된 아이디입니다."),
    NOT_EXIST_MEMBER("1002", "존재하지 않는 회원입니다."),
    ALREADY_REGISTERED_MEMBER_BY_NICK_NAME("1105", "이미 등록된 닉네임입니다."),




    // === other (9000) ============================================================================================================
    JSON_PROCESS_FAIL("9001", "Json 파일을 처리하는데 실패했습니다. "),
    CONSTRAINT_PROCESS_FAIL("9002", "정보가 서로 일치하지 않습니다."),


    FAILED("9999", "Unexpected Error");

    private static final Map<String, ErrorCode> errorMap =
            Arrays.stream(values()).collect(Collectors.toMap(ErrorCode::getCode, e -> e));
    private String code;
    private String errorMessage;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.errorMessage = msg;
    }

    public static ErrorCode findByCode(String code) {
        return errorMap.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorResult getErrorResponse() {
        return new ErrorResult(code, errorMessage);
    }

    public Map<String, String> getErrorMap() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", code);
        errorMap.put("message", errorMessage);
        return errorMap;
    }

}
