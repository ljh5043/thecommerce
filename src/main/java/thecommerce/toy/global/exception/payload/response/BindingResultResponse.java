package thecommerce.toy.global.exception.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BindingResultResponse {

    private boolean success;        // 성공 여부
    private String path;            // 요청 경로
    private String method;          // 요청 메소드
    private String errorCode;       // 에러 코드
    private String errorMessage;    // 에러 메시지
    private Object content;         // 에러 내용

    public BindingResultResponse(boolean success, String path, String method, String errorCode, String errorMessage, Object content) {
        this.success = success;
        this.path = path;
        this.method = method;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.content = content;
    }
}
