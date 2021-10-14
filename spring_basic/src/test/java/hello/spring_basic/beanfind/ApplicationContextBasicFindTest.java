package hello.spring_basic.beanfind;

import hello.spring_basic.AppConfig;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 빈 이름, 타입으로 조회

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); // 타입으로만 조회 (타입이 인터페이스 명임)

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 위에는 인터페이스로 타입으로 조회한 것임 (-> 인터페이스의 구현체가 대상이되어 조회됨)

    @Test
    @DisplayName("구체 타입으로 조회") // 인터페이스가 아닌 구체 타입으로 조회
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 타입이 인터페이스 명이 아닌 구체 타입의 명임

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 타입이 꼭 인터페이스 일 필요는 없음
    // AppConfig 에서 보면
    // @Bean
    // public MemberService memberService() { //MemberService를 Appconfig에서 만듬
    //        return new MemberServiceImpl(memberRepository());
    // 반환타입이 MemebrService로 인터페이스 여야할 것 같지만
    // return에서 보이는 것과 같이 Bean에 등록된 인스턴스 타입을 통해 결정되기 때문에 꼭 인터페이스 일 필요는 없음

    // 물론 구체 타입으로 조회하는 것은 좋지 않은 방법임 (가능 하지만 )
    // 왜냐하면 역할과 구현을 구분하고 역할에 의존하다록 코드를 작성하는 것이 좋다.

    // 실패 테스트도 만들어주기
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // ac.getBean("xxxxx", MemberService.class);
        // 존재하지 않는 빈 이름 "xxxxx"로 조회
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
        // () -> 이후에 있는 로직을 실행하면 왼쪽에 있는 예외가 터져야한다. (예외가 던저저야 성공)

    }
}
