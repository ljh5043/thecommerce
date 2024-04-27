package thecommerce.toy.domain.member.repository.custom;


import org.springframework.data.domain.Page;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.FindAllByPagingRequest;
import thecommerce.toy.domain.member.payload.response.MemberResponse;


public interface MemberRepositoryCustom {

    Page<MemberResponse> findAllByPaging(FindAllByPagingRequest request);

    Member findRandomMemberWithExistingData();
}
