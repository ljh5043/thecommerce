package thecommerce.toy.domain.member.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindByLoginIdRequest {

    private String loginId;

    public FindByLoginIdRequest(String loginId) {
        this.loginId = loginId;
    }
}
