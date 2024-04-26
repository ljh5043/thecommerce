package thecommerce.toy.domain.member.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import thecommerce.toy.domain.member.entity.Member;

@Data
@NoArgsConstructor
public class SaveNewMemberResponse {

    private long id;
    private String loginId;

    public SaveNewMemberResponse(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
    }
}
