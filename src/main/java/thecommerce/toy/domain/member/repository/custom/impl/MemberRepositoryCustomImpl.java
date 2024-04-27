package thecommerce.toy.domain.member.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import thecommerce.toy.domain.member.entity.QMember;
import thecommerce.toy.domain.member.enums.SortTypeForFindAll;
import thecommerce.toy.domain.member.payload.request.FindAllByPagingRequest;
import thecommerce.toy.domain.member.payload.response.MemberResponse;
import thecommerce.toy.domain.member.repository.custom.MemberRepositoryCustom;

import java.util.List;


@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    //    querydsl
    private final JPAQueryFactory jpaQueryFactory;

    QMember member = QMember.member;

    @Override
    public Page<MemberResponse> findAllByPaging(FindAllByPagingRequest request) {

        Sort sort;
        if (request.getSort() == SortTypeForFindAll.CREATED_AT) {
            sort = Sort.by(Sort.Direction.DESC, "createdAt");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "name");
        }

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize(), sort);

        JPAQuery<MemberResponse> jpaQuery = jpaQueryFactory.select(Projections.constructor(MemberResponse.class,
                        member.loginId,
                        member.nickName,
                        member.name,
                        member.phone,
                        member.email))
                .from(member);

        List<MemberResponse> content = jpaQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //  전체 사이즈를 가져올 때 람다식 표현을 사용한 이유, 지연로딩을 활용하기 위함
        //  필요한 시점에 쿼리를 실행하여 결과를 가져올 수 있도록 하고 이는 성능 향상 및 불필요한 쿼리 실행을 방지함
        //  jpaQuery.fetch().size()를 직접 호출하는 것은 메소드 실행 시점에서 즉시 쿼리를 실행하여 결과를 반환
        //  이는 메소드를 호출할 때마다 쿼리가 실행되어 자원을 소모
        return PageableExecutionUtils.getPage(content, pageable, () -> jpaQuery.fetch().size());
    }
}
