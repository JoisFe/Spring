package hello.spring_basic.member;

import hello.spring_basic.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // 각 테스트 실행전 호출
    // 테스트 실행 전에 appConfig 만들고 memberService를 할당함
    // @Test가 두번 있으면 이 부분 두번 호출
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    // 기존에 직접 MemberServiceImpl을 생성하는 것을 지우자 -> DIP 어기지 않기위해

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
