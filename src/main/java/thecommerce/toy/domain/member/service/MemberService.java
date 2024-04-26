package thecommerce.toy.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.FindByLoginIdRequest;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.payload.response.SaveNewMemberResponse;
import thecommerce.toy.domain.member.repository.MemberRepository;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    //  create
    @Transactional
    public SaveNewMemberResponse saveNewMember(SaveNewMemberRequest request) {
        Member member = new Member(request);
        Member savedMember = memberRepository.save(member);
        savedMember.completeSave();
        return new SaveNewMemberResponse(savedMember);
    }

    //  read
    public Member findByLoginId(FindByLoginIdRequest request) {
        return memberRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> new GlobalException(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_LOGIN_ID));
    }

    //  update


    //  delete

}
