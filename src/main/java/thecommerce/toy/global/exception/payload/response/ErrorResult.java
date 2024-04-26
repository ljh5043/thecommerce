package thecommerce.toy.global.exception.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResult {

    private String code;            // 에러 코드
    private String errorMessage;    // 에러 메시지

    public ErrorResult(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
