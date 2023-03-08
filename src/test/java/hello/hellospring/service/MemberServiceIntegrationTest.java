package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // Test 시작 전에 트랜잭션은ㄹ 시작하고 테스트 완료 후에 항상 rollback 한다. => 동일한 테스트케이스를 여러번 수행할 수 있다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; // Test Class에서는 편한 방법으로 Field Injection 방식을 사용한다.

    @Autowired MemberRepository memberRepository;

    @Test
    // @Commit -> 실제 DB에 반영
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// member2를 join하면 IllegalStateException이 수행되어야 함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }

}