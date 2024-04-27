package thecommerce.toy.domain.member.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.repository.MemberRepository;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveNewMemberRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveNewMemberRequest request = (SaveNewMemberRequest) target;
        Optional<Member> findByLoginId = memberRepository.findByLoginId(request.getLoginId());
        Optional<Member> findByNickName = memberRepository.findByNickName(request.getNickName());
        if (findByLoginId.isPresent()) {
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_LOGIN_ID);
        }
        if (findByNickName.isPresent()) {
            throw new GlobalException(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_NICK_NAME);
        }

    }
}
