package hello.hellospring.repository;

import hello.hellospring.domain.Member;
// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 클래스 레벨에서 테스트케이스를 실행하면 어떤 테스트케이스가 먼저 실행될지 순서가 보장되지 않는다.
     * 따라서 각 테스트가 수행된 후 저장소를 clear 해주기 위한 함수를 선언한다.
     * @AfterEach : 각 테스트케이스가 끝난 이후에 함수를 수행시킨다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();// Optional에서 값을 꺼낼 때 get()을 사용해서 꺼낼 수 있음

        // Assertions.assertEquals(member, result);

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        // repository를 clear하는 AfterEach 함수를 선언하지 않고 본 클래스의 전체 테스트케이스를 수행할 경우 findAll() 함수에서 저장한 spinrg1에 해당하는 객체가 리턴되어 오류 발생함.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
