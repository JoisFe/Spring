package hello.spring_basic.discount;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;

public class RateDiscountPolicy implements DiscountPolicy { // 이전에 작성한 인터페이스인 DiscountPolicy 상속

    private int discountPercent = 10; // 10 % 할인 할 것이니

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; // 해당 회원이 VIP라면 10% 할인

            // return price * (discountPercnet / 100) 하면 discountPercnet가 100이 아닌 이상 전부 결과가 0이 됨
            // discountPercnt / 100 결과가 0.~~ 인 경우 int 형으로 0이 되기 때문 !! 조심
        }

        else {
            return 0; // 회원이 VIP 아닐 시 할인 적용 X
        }
    }
}
