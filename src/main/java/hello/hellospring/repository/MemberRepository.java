package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional: null이 올 수 있는 값을 감싸는 Wrapper 클래스
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
