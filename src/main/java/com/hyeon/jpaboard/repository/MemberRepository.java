package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberEmail(String email);
    boolean existsByMemberEmail(String email); //방식이 두가지
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByMemberEmail(String userEmail);
}
