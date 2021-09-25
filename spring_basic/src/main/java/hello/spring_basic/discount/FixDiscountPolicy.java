package hello.spring_basic.discount;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // 현재 회원이 VIP 등급이면
            return discountFixAmount; // discountFixAmount 리턴 (즉 1000원 리턴)
        }

        else { // 현재 회원이 VIP가 아니라면
            return 0; // 0을 리턴해라
        }
    }
}
