package hello.spring_basic;

import hello.spring_basic.discount.DiscountPolicy;
import hello.spring_basic.discount.FixDiscountPolicy;
import hello.spring_basic.discount.RateDiscountPolicy;
import hello.spring_basic.member.MemberRepository;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import hello.spring_basic.member.MemoryMemberRepository;
import hello.spring_basic.order.OrderService;
import hello.spring_basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션에 전체 동장 방식을 구성하는 것을 AppConfig에서 하자

@Configuration // @Configuration 어노테이션 아래에는 애플리케이션의 설정 정보(구성 정보) 적어줌
public class AppConfig {
    @Bean // @Bean 어노테이션 아래에 있는 것은 Spring Container에 등록이 됨
    // memberService 역할
    public MemberService memberService() { //MemberService를 Appconfig에서 만듬
        return new MemberServiceImpl(memberRepository());
        // 리턴타입이 기존에는 구체 클래스인 new MemoryMemberRepository()에서 인터페이스 반환하는 memberRepository() 메서드로 바꿔주었다.
        // memberRepository() 메서드는 아래에 구현되어있음

        // 누군가 AppConfig 통해 memberSe rvice() 불러다 쓸떄 MemberServiceImpl인 구현체의 객체가 생성되어 반환되는데
        // 그떄 거기에 new MemoryMemberRepository() 들어감
        // 즉 MemberServiceImpl의 생성자의 매개변수로 MemoryMemberRepository의 객체가 들어감
    }

    @Bean
    // memberRepository 역할
   public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        //
    }

    @Bean
    // orderService 역할
    public OrderService orderService() { // 위와 마찬가지
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 리턴 타입이 기존에는 구체 클래스인 new MemoryMemberRepository()에서 인터페이스 반환하는 memberRepository() 메서드로
        // 구체 클래스인 new FixDiscountPolicy()에서 인터페이스 반환하는 discountPolicy() 메서드로 변경
        // memberRepository() 메서드는 위에서, discountPolicy() 메서드는 아래에서 구현되어있음

        // 누군가 AppConfig를 통해 orderService()를 조회하면 OrderServiceImpl 구현체의 객체가 생성되어 반환하는데
        // OrderServiceImpl 클래스를 보면
        // OrderServiceImpl의 생성자에 두 매개변수가 필요한
        // MemberRepository 객체와, DiscountPolicy 객체 2개 모두 필요하므로
        // OrderServiceImpl의 생성자의 매개변수로 MemoryMemberRepository의 객체와, FixDiscountPolicy 객체 2개 모두 들어감
    }

    @Bean
    // discountPolicy 역할
    public DiscountPolicy discountPolicy() {

        // return new FixDiscountPolicy(); // -> 기존 정액 할인 정책에서 정률 할인정책으로 변경 위해 코드 지움

        return new RateDiscountPolicy(); // 정률 할인 정책으로 변경
    }

    // 위 처럼 구현하면 코드를 보기만 해도 메서드 명을 보는 순간 역할이 다 보임
    // 특정 역할 변경이 일어날때 해당 역할 부분 메서드만을 변경하면 된다는 장점이 있음
}