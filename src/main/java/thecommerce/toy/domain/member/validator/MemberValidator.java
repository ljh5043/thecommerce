package thecommerce.toy.domain.member.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.repository.MemberRepository;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void isLoginIdAndNickNameUnique(SaveNewMemberRequest request) {
        Optional<Member> findByLoginId = memberRepository.findByLoginId(request.getLoginId());
        Optional<Member> findByNickName = memberRepository.findByNickName(request.getNickName());
        if(findByLoginId.isPresent()){
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_LOGIN_ID);
        }
        if(findByNickName.isPresent()){
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_NICK_NAME);
        }
    }

}
