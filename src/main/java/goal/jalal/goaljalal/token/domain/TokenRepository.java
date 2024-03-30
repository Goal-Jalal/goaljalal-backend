package goal.jalal.goaljalal.token.domain;

import goal.jalal.goaljalal.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByRefreshToken(final String refreshToken);

    boolean existsByRefreshToken(final String refreshToken);

    Optional<Token> findByMember(final Member member);

    @Query("DELETE from Token t where t.member.id = :memberId")
    @Modifying
    void deleteByMemberId(final Long memberId);
}
