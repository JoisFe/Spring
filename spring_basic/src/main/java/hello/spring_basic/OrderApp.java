package hello.spring_basic;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import hello.spring_basic.order.Order;
import hello.spring_basic.order.OrderService;
import hello.spring_basic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl(); //memberservice 만들고
        OrderService orderService = new OrderServiceImpl(); //orderservice 만듬

        Long memberId = 1l; //멤버 아이디 생성
        Member member = new Member(memberId, "memberA", Grade.VIP); // Member 객체 생성 (vip 회원 만듬)
        memberService.join(member); //memberService를.join 통해 메모리 객체에 넣어둠 -> 그래야 주문에서 찾아 쓸 수 있으니

        Order order = orderService.createOrder(memberId, "iteA", 10000); //orderService.createOrder를 통해 order 생성

        System.out.println("order =" + order); //order.toString()으로 정의한 내용들이 출력 (order클래스를 보면 toString()함수 정의해놨음)
        //order라는 객체 자체를 출력했으므로 order내의 toString()함수가 호출 됨
    }
}
