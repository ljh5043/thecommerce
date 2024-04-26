package thecommerce.toy.global.exception.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import thecommerce.toy.global.api.RestApiController;
import thecommerce.toy.global.event.ExceptionEvent;
import thecommerce.toy.global.exception.GlobalException;
import thecommerce.toy.global.exception.payload.response.BindingResultResponse;

import javax.servlet.http.HttpServletRequest;

// 예외 처리
public class RestApiControllerAdvice extends RestApiController {

    private final ApplicationEventPublisher applicationEventPublisher;


    public RestApiControllerAdvice(ObjectMapper objectMapper, ApplicationEventPublisher applicationEventPublisher) {
        super(objectMapper);
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // 컨트롤러를 거친 이후 Event - Log
    protected void sendLogEvent(GlobalException exception, HttpServletRequest httpServletRequest) {
        applicationEventPublisher.publishEvent(ExceptionEvent.createExceptionEvent(exception,httpServletRequest));
    }

    // 컨트롤러를 거치기 전 바인딩 리절트 관련 이슈가 터지면 error 컨트롤러로 보내서 해당 Event - Log
    protected void sendLogEvent(BindingResultResponse response) {
        applicationEventPublisher.publishEvent(ExceptionEvent.createExceptionEventBinding(response));
    }
}
