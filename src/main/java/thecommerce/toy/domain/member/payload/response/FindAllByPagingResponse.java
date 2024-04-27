package thecommerce.toy.domain.member.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import thecommerce.toy.global.util.paging.PageInfo;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllByPagingResponse {
    private List<MemberResponse> memberList;
    private PageInfo pageInfo;
}
