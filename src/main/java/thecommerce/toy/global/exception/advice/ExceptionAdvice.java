package thecommerce.toy.global.exception.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.error.utils.ErrorUtil;
import thecommerce.toy.global.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;


// 예외 처리
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "thecommerce.toy")
public class ExceptionAdvice extends RestApiControllerAdvice {

    // utils
    private final ErrorUtil errorUtil;

    public ExceptionAdvice(ObjectMapper objectMapper, ApplicationEventPublisher applicationEventPublisher, ErrorUtil errorUtil) {
        super(objectMapper, applicationEventPublisher);
        this.errorUtil = errorUtil;
    }

    // Global Exception Catch
    @ExceptionHandler(value = GlobalException.class)
    protected ResponseEntity<String> processCommonException(GlobalException globalException, HttpServletRequest httpServletRequest) {
        ErrorCode errorCode = globalException.getErrorCode();
        // Event - Log
        return createFailRestResponse(errorCode.getErrorResponse());
    }
}
