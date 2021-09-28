package hello.spring_basic.order;

import hello.spring_basic.discount.DiscountPolicy;
import hello.spring_basic.discount.FixDiscountPolicy;
import hello.spring_basic.discount.RateDiscountPolicy;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberRepository;
import hello.spring_basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //OrderService는 2개가 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // memberRepository에서 회원 찾아야 하므로
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // discountPolicy에서 할인 정책되로 적용 해야하므로
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정액 할인에서 정률 할인으로 바꿈
    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 변경 (구체 클래스에 의존 하지 않음)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId); // member를 일단 찾자..

        int discountPrice = discountPolicy.discount(member, itemPrice);
        //OrderService 입장에선 할인에 대해서느 잘 모르겠으니 discountPolicy 에게 그 일을 맡기고 결과만 받는 다고 생각
        //-> 설계가 잘 된 (SRP (단일책임원칙) 잘 지킨 것)
        //만약 할인에 대해 변경할 사항이 있으면 할인에 대한 부분만 고치면 되고 주문에 관련된 부분은 고칠 필요가 없기 때문에

        return new Order(memberId, itemName, itemPrice, discountPrice); //Order를 만들어 반환.

        /*
        정리하면 주문 생성요청이 오면 회원정보를 먼저 조회를 하고 할인 정책에 회원을 넘기고
        결과들을 이용해 주문을 만들어 반환한다.
         */
    }
}
