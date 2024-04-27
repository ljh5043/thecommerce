package thecommerce.toy.domain.member.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private String loginId;
    private String nickName;
    private String name;
    private String phone;
    private String email;

}
