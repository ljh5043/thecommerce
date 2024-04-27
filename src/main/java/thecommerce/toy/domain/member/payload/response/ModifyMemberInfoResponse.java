package thecommerce.toy.domain.member.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyMemberInfoResponse {

    private BeforeInfo beforeInfo;
    private AfterInfo afterInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BeforeInfo {
        private String beforePhone;
        private String beforeEmail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AfterInfo {
        private String afterPhone;
        private String afterEmail;
    }
}
