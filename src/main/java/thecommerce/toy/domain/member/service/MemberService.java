package thecommerce.toy.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.FindAllByPagingRequest;
import thecommerce.toy.domain.member.payload.request.FindByLoginIdRequest;
import thecommerce.toy.domain.member.payload.request.ModifyMemberInfoRequest;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.payload.response.FindAllByPagingResponse;
import thecommerce.toy.domain.member.payload.response.MemberResponse;
import thecommerce.toy.domain.member.payload.response.ModifyMemberInfoResponse;
import thecommerce.toy.domain.member.payload.response.SaveNewMemberResponse;
import thecommerce.toy.domain.member.repository.MemberRepository;
import thecommerce.toy.domain.member.validator.MemberValidator;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;
import thecommerce.toy.global.util.paging.PageUtil;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    //  repository
    private final MemberRepository memberRepository;

    //  util
    private final PageUtil pageUtil;
    private final MemberValidator memberValidator;

    //  create
    //  신규 회원 생성
    @Transactional
    public SaveNewMemberResponse saveNewMember(SaveNewMemberRequest request) {
        memberValidator.isLoginIdAndNickNameUnique(request);
        Member member = new Member(request);
        Member savedMember = memberRepository.save(member);
        savedMember.completeSave();
        return new SaveNewMemberResponse(savedMember);
    }

    //  read
    //  로그인 아이디로 회원 정보가져오기
    public Member findByLoginId(FindByLoginIdRequest request) {
        return memberRepository.findByLoginId(request.getLoginId()).orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_MEMBER));
    }

    //  회원 목록 조회
    public FindAllByPagingResponse findAllByPaging(FindAllByPagingRequest request) {
        Page<MemberResponse> responses = memberRepository.findAllByPaging(request);
        if (!(responses.getTotalElements() > 0)) {
            throw new GlobalException(ErrorCode.CAN_NOT_FOUND_MEMBER_DATA);
        }
        return new FindAllByPagingResponse(responses.toList(), pageUtil.getPageInfo(responses, request.getPage()));
    }


    //  update
    @Transactional
    public ModifyMemberInfoResponse update(String loginId, ModifyMemberInfoRequest request) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_MEMBER));
        ModifyMemberInfoResponse.BeforeInfo beforeInfo = new ModifyMemberInfoResponse.BeforeInfo(member.getPhone(), member.getEmail());
        member.update(request);
        ModifyMemberInfoResponse.AfterInfo afterInfo = new ModifyMemberInfoResponse.AfterInfo(member.getPhone(), member.getEmail());
        return new ModifyMemberInfoResponse(beforeInfo, afterInfo);
    }

    //  delete

}
