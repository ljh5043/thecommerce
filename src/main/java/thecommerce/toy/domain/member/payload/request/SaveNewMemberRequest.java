package thecommerce.toy.domain.member.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class SaveNewMemberRequest {

    @NotBlank(message = "로그인 아이디를 입력해주세요.")
    @Schema(description = "로그인 아이디", example = "test1234")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,15}$",
            message = "비밀번호는 6~15자의 영어, 숫자, 특수문자 조합으로 설정되어야 합니다.")
    @Schema(description = "비밀번호", example = "test123!@#")
    String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Schema(description = "닉네임", example = "tester")
    private String nickName;

    @NotBlank(message = "이름을 입력해주세요.")
    @Schema(description = "이름", example = "테스터")
    private String name;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Pattern(regexp = "\\d{9,}", message = "숫자 9자 이상을 입력해 주세요.")
    @Schema(description = "전화번호", example = "01011112222")
    private String phone;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
    @Schema(description = "이메일", example = "testemail@test.test")
    private String email;

    public SaveNewMemberRequest(String loginId, String password, String nickName, String name, String phone, String email) {
        this.loginId =loginId;
        this.password = password;
        this.nickName = nickName;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
