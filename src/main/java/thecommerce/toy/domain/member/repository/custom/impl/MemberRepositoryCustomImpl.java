package thecommerce.toy.domain.member.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import thecommerce.toy.domain.member.repository.custom.MemberRepositoryCustom;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    //    querydsl
    private final JPAQueryFactory jpaQueryFactory;
}
