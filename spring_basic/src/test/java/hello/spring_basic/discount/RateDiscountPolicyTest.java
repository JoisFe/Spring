package hello.spring_basic.discount;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest { // VIP 회원이 10% 할인이 잘 되는지 테스트 해보자

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP); // 회원이 VIP임

        //when
        int discount = discountPolicy.discount(member, 10000); //가격이 10000원일때 할인되는 가격

        //then
        assertThat(discount).isEqualTo(1000); // 10000원의 10% 할인가격은 1000원 되어야하므로
                                                         // discount가 1000원이 되는지 확인해봐야한다.
    }

    // 테스트에서 중요한 점은 성공테스트뿐만 아니라 실패테스트도 만들어 봐야한다.
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC); // 회원이 VIP가 아닌 일반 회원임

        //when
        int discount = discountPolicy.discount(member, 10000); // 가격이 10000원일때 할인되는 가격

        //then
        assertThat(discount).isEqualTo(0); // 회원이 VIP가 아니기 떄문에 할인 가격이 0원 되야함.
    }
}