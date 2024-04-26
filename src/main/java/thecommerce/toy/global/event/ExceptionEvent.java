package thecommerce.toy.global.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;
import thecommerce.toy.global.exception.payload.response.BindingResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

//  예외 발생 시, 예외 정보를 담는 이벤트 객체
@Data
@NoArgsConstructor
public class ExceptionEvent {

    protected String requestPath;
    protected String requestMethod;
    protected String errorName;
    protected ErrorCode errorCode;
    protected String errorDetailMsg;
    protected LocalDateTime createdAt;


    //  예외 발생 시, 예외 정보를 담는 이벤트 객체
    public static ExceptionEvent createExceptionEvent(GlobalException exception, HttpServletRequest httpServletRequest) {
        ExceptionEvent exceptionEvent = new ExceptionEvent();
        exceptionEvent.setRequestPath(httpServletRequest.getRequestURL().toString());
        exceptionEvent.setRequestMethod(httpServletRequest.getMethod());
        exceptionEvent.setErrorName(exception.getClass().getSimpleName());
        exceptionEvent.setErrorCode(exception.getErrorCode());
        exceptionEvent.setErrorDetailMsg(exception.getErrorDetailMessage());
        exceptionEvent.setCreatedAt(LocalDateTime.now());
        return exceptionEvent;
    }

    //  예외 발생 시, 바인딩 리절트 에러 정보를 담는 이벤트 객체
    public static ExceptionEvent createExceptionEventBinding(BindingResultResponse response) {
        ExceptionEvent exceptionEvent = new ExceptionEvent();
        GlobalException exception = new GlobalException(ErrorCode.REQUEST_BINDING_RESULT);
        exceptionEvent.setRequestPath(response.getPath());
        exceptionEvent.setRequestMethod(response.getMethod());
        exceptionEvent.setErrorName(exception.getClass().getSimpleName());
        exceptionEvent.setErrorCode(exception.getErrorCode());
        exceptionEvent.setErrorDetailMsg(response.getContent().toString());
        exceptionEvent.setCreatedAt(LocalDateTime.now());
        return exceptionEvent;
    }

    //  예외 발생 시, 이벤트 로그 생성
    public String getExceptionString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nlogStart=== === === === === === === === === === === === === === === === === === === === === === === === === === logStart\n");
        stringBuilder.append("Exception Title : ").append(errorName).append("\n");


        // 1. Set Request Info
        stringBuilder.append("Request Path : ").append(requestPath).append("\n");
        stringBuilder.append("Request Method : ").append(requestMethod).append("\n");

        // 2. Set User Info

        // 3. Set Exception
        if (this.errorCode != null) {
            stringBuilder.append("Error Code & Msg : ").append(errorCode.getCode()).append(" / ").append(errorCode.getErrorMessage()).append("\n");
        }

        // 4. Occur Date
        stringBuilder.append("createDate : ").append(createdAt.toString()).append("\n\n");



        // 5. Set Error Detail Msg
        stringBuilder.append(errorDetailMsg);
        stringBuilder.append("\nlogEnd=== === === === === === === === === === === === === === === === === === === === === === === === === === logEnd\n\n");

        return stringBuilder.toString();
    }

}
