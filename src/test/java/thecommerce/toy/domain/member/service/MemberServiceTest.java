package thecommerce.toy.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.enums.SortTypeForFindAll;
import thecommerce.toy.domain.member.payload.request.FindAllByPagingRequest;
import thecommerce.toy.domain.member.payload.request.FindByLoginIdRequest;
import thecommerce.toy.domain.member.payload.request.SaveNewMemberRequest;
import thecommerce.toy.domain.member.payload.response.FindAllByPagingResponse;
import thecommerce.toy.domain.member.payload.response.MemberResponse;
import thecommerce.toy.domain.member.payload.response.SaveNewMemberResponse;
import thecommerce.toy.domain.member.repository.MemberRepository;
import thecommerce.toy.global.error.enums.ErrorCode;
import thecommerce.toy.global.exception.GlobalException;
import thecommerce.toy.global.util.paging.PageUtil;
import thecommerce.toy.global.util.random.RandomStringUtil;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PageUtil pageUtil;
    @Autowired
    private RandomStringUtil randomStringUtil;

    @Test
    void saveNewMember() {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            //  given
            SaveNewMemberRequest request = new SaveNewMemberRequest(randomStringUtil.generateRandomString(6),
                    randomStringUtil.generateRandomString(10),
                    randomStringUtil.generateRandomString(6),
                    randomStringUtil.generateRandomString(6),
                    "01011112222",
                    randomStringUtil.generateRandomString(4) + "@" + randomStringUtil.generateRandomString(6) + "." + randomStringUtil.generateRandomString(3));
            //  when
            if (memberRepository.existsByNickName(request.getNickName())){
                GlobalException exception = assertThrows(
                        GlobalException.class,
                        () -> memberService.saveNewMember(request)
                );
                //  then
                assertEquals(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_NICK_NAME, exception.getErrorCode());
            } else if (memberRepository.existsByLoginId(request.getLoginId())){
                GlobalException exception = assertThrows(
                        GlobalException.class,
                        () -> memberService.saveNewMember(request)
                );
                //  then
                assertEquals(ErrorCode.ALREADY_REGISTERED_MEMBER_BY_LOGIN_ID, exception.getErrorCode());
            } else {
                SaveNewMemberResponse member = memberService.saveNewMember(request);
                Member findByLoginId = memberService.findByLoginId(new FindByLoginIdRequest(request.getLoginId()));
                //  then
                assertEquals(member.getId(), findByLoginId.getId());
                assertEquals(member.getLoginId(), findByLoginId.getLoginId());
            }
        }
    }

    @Test
    void findAllByPaging() {
        saveNewMember();
        Random random = new Random();

        for (int i = 0; i < random.nextInt(100); i++) {
            SortTypeForFindAll[] values = SortTypeForFindAll.values();
            int randomIndex = random.nextInt(values.length);
            int page = random.nextInt(1,10);
            int pageSize = random.nextInt(1,10);
            SortTypeForFindAll sortType = values[randomIndex];

            //  given
            FindAllByPagingRequest request = new FindAllByPagingRequest(page, pageSize, sortType);
            //  when
            int totalMemberCount = memberRepository.findAll().size();
            if (totalMemberCount > 0 && totalMemberCount > (page-1) * pageSize) {
                FindAllByPagingResponse response = memberService.findAllByPaging(request);
                //  then
                assertNotNull(response);
                assertEquals(request.getPage(), response.getPageInfo().getPage());
            } else {
                GlobalException exception = assertThrows(
                        GlobalException.class,
                        () -> memberService.findAllByPaging(request)
                );
                //  then
                assertEquals(ErrorCode.CAN_NOT_FOUND_MEMBER_DATA, exception.getErrorCode());
            }
        }

    }
}