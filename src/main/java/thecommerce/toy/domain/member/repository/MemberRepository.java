package thecommerce.toy.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thecommerce.toy.domain.member.entity.Member;
import thecommerce.toy.domain.member.repository.custom.MemberRepositoryCustom;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {

    Optional<Member> findByLoginId(String loginId);

}
