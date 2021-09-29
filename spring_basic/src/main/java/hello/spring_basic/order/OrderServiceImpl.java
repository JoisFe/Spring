package hello.spring_basic.order;

import hello.spring_basic.discount.DiscountPolicy;
import hello.spring_basic.discount.FixDiscountPolicy;
import hello.spring_basic.discount.RateDiscountPolicy;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberRepository;
import hello.spring_basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //OrderService는 2개가 필요
    private final MemberRepository memberRepository;
    // new MemoryMemberRepository(); 지움
    // memberRepository의 구현체 선택을 여기서 바로 하지 말자 -> DIP 꺠트리기 때문
    // 즉 인터페이스에만 의존하도록 변경 (구체 클래스에 의존 하지 않음)

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // discountPolicy에서 할인 정책되로 적용 해야하므로
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정액 할인에서 정률 할인으로 바꿈
    private DiscountPolicy discountPolicy;
    // new FixDiscountPolicy(); 지움
    // discountPolicy의 구현체 선택을 여기서 바로 하지 말자 -> DIP 꺠트리기 때문
    // 즉 인터페이스에만 의존하도록 변경 (구체 클래스에 의존 하지 않음)

    // 생성자를 만들자
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

        // 자 이렇게 코드를 고치고 나니 OrderServiceImpl에 MemoryMemberRepository와 FixDiscontPolicy에 대한 부분이 없다.
        // 오로지 MemberRepository, DiscountPolicy라는 인터페이스만 존재한다.
        // 즉 추상화만 의존한다. -> 드디어 DIP를 지켰다.
        // 구체적인것에 대한것은 OrderServiceImpl은 전혀 모른다.

        // 구체적인 것에 대한 것은 생성자를 통해 밖에서(AppConfig) 생성해서 넣어줌
        // 즉 생성자를 통해 객체가 (new MemoryMemberRepository(), new FixDiscountPolicy())가 생성되어 들어감
        // -> "생성자 주입" 이라고 함.
    }

    // OrderServiceImpl 클래스는 인터페이스에만 의존하고 있으므로
    // 구체적인 클래스에 대해서 전혀 모름
    // 누군가가 memberRepository에 MemoryMemberRepository를 넣어줄지, DBMemberRepository를 넣어줄지 모른다.
    // discountPolicy에 FixDiscountPolicy를 넣어줄지 RateDiscountPolicy를 넣어줄지 모른다.
    // 철저하게 DIP를 지키고 있음을 알 수 있다.

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
