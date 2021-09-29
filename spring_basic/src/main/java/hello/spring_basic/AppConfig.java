package hello.spring_basic;

import hello.spring_basic.discount.FixDiscountPolicy;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import hello.spring_basic.member.MemoryMemberRepository;
import hello.spring_basic.order.OrderService;
import hello.spring_basic.order.OrderServiceImpl;

// 애플리케이션에 전체 동장 방식을 구성하는 것을 AppConfig에서 하자
public class AppConfig {

    public MemberService memberService() { //MemberService를 Appconfig에서 만듬
        return new MemberServiceImpl(new MemoryMemberRepository());

        // 누군가 AppConfig 통해 memberService() 불러다 쓸떄 MemberServiceImpl인 구현체의 객체가 생성되어 반환되는데
        // 그떄 거기에 new MemoryMemberRepository() 들어감
        // 즉 MemberServiceImpl의 생성자의 매개변수로 MemoryMemberRepository의 객체가 들어감
    }

    public OrderService orderService() { // 위와 마찬가지
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        // 누군가 AppConfig를 통해 orderService()를 조회하면 OrderServiceImpl 구현체의 객체가 생성되어 반환하는데
        // OrderServiceImpl 클래스를 보면
        // OrderServiceImpl의 생성자에 두 매개변수가 필요한
        // MemberRepository 객체와, DiscountPolicy 객체 2개 모두 필요하므로
        // OrderServiceImpl의 생성자의 매개변수로 MemoryMemberRepository의 객체와, FixDiscountPolicy 객체 2개 모두 들어감
    }
}