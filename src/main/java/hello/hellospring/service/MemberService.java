package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository.*;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입

    public long join(Member member){    // create
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member); // 중복 회원 검증


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회 - read
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
