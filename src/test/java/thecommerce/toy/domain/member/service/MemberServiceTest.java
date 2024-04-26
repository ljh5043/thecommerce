package thecommerce.toy.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.payload.request.FindByLoginIdRequest;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.payload.response.SaveNewMemberResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void saveNewMember() {
        //  given
        SaveNewMemberRequest request = new SaveNewMemberRequest("test1234", "test123!@#", "tester", "테스터", "01011112222", "testemail@test.test");
        //  when
        SaveNewMemberResponse member = memberService.saveNewMember(request);
        Member findByLoginId = memberService.findByLoginId(new FindByLoginIdRequest("test1234"));
        //  then
        assertEquals(member.getId(), findByLoginId.getId());
        assertEquals(member.getLoginId(), findByLoginId.getLoginId());
    }
}