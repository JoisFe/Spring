package hello.spring_basic;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import hello.spring_basic.order.Order;
import hello.spring_basic.order.OrderService;
import hello.spring_basic.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        // AppConfig appConfig = new AppConfig();

        // MemberService memberService = appConfig.memberService(); // memberService 필요시 appConfig에서 인터페이스 만듬
        // memberService에는 MemberServiceImpl 객체인데 생성자로 MemoryMemberRepository()를 사용하는 것을 주입 (AppConfig에 있음)

        // OrderService orderService = appConfig.orderService(); // orderService 필요시 appConfig에서 인터페이스 만듬
        // orderService에는 OrderServiceImpl 객체인데 생성자로 MemoryMemberRepository()와 FixDiscountPolicy()를 사용하는 것을 주입 (AppConfig에 있음)

        // 기존에 main 메서드에서 직접 MemberServiceImpl, OrderServiceImpl을 생성함
        // -> DIP 어기기 떄문거

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        // 스프링 컨테이너에 등록한 AppConfig에 있는 memberService, orderService 가져오고 싶을때

        Long memberId = 1l; //멤버 아이디 생성
        Member member = new Member(memberId, "memberA", Grade.VIP); // Member 객체 생성 (vip 회원 만듬)
        memberService.join(member); //memberService를.join 통해 메모리 객체에 넣어둠 -> 그래야 주문에서 찾아 쓸 수 있으니

        Order order = orderService.createOrder(memberId, "iteA", 20000); //orderService.createOrder를 통해 order 생성

        System.out.println("order =" + order); //order.toString()으로 정의한 내용들이 출력 (order클래스를 보면 toString()함수 정의해놨음)
        //order라는 객체 자체를 출력했으므로 order내의 toString()함수가 호출 됨
    }
}
