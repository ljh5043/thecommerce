package thecommerce.toy.domain.member.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMemberInfoRequest {

    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Pattern(regexp = "\\d{9,}", message = "숫자 9자 이상을 입력해 주세요.")
    @Schema(description = "전화번호", example = "01022223333")
    private String phone;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
    @Schema(description = "이메일", example = "modifyemail@test.test")
    private String email;
}
