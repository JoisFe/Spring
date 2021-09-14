package hello.spring_basic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given : 이런 이런 환경 주어졌을때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 이렇게 했을때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이렇게 된다. -> 검증
        Assertions.assertThat(member).isEqualTo(findMember);

        //정리하면 새로운 member가 주어졌을때
        // 그 새로운 멤버를 회원가입(등록) 했을때
        // member와 findMember가 같아야 한다.
    }
}
