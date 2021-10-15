package hello.spring_basic.beanfind;

import hello.spring_basic.discount.DiscountPolicy;
import hello.spring_basic.discount.FixDiscountPolicy;
import hello.spring_basic.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class); // 타입으로 빈 조회
        // 해당 DiscountPolicy 는 부모 클래스 타입임
        // 아래 TestConfig에서 스프링 컨테이너에 담은 스프링 빈은 타입이 RateDiscountPolicy, FixDiscountPolicy로
        // DiscountPolicy의 자식 클래스들 이다.
        // 따라서 두 빈 모두 조회 된다. -> assertThrows로 처리해야

        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    // 부모 클래스 타입으로 조회했더니 여러 자식 클래스 까지 같이 조회되며 하나의 빈이 아닌 여러 빈이 조회되어 에러가 나게 된다.
    // 이러한 경우는 저번에 배웠듯 빈 이름까지 지정하여 조회하면 된다.
    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        // 빈 이름 rateDiscountPolicy, 타입 DiscountPolicy
        // 타입은 DiscountPolicy 이지만 실제 구현 객체인 rateDiscountPolicy가 조회 될 것임

        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    // 또 다른 방법으로 특정 하위 타입으로 조회하면 됨
    // 물론 이 방법은 좋지 않은 방법임 !! (역할과 구현을 구분하고 역할에 의존하다록 코드를 작성해야 좋으므로)
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        // 특정 하위 타입 으로 빈 조회함.

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    // 이제는 부모 타입으로 부모와 그 자식에 해당하는 모든 빈을 조회해볼 것이다.
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        assertThat(beansOfType.size()).isEqualTo(2); //DiscountPolicy 클래스에 스프링 컨테이너에 등록한 스프링 빈은 2개일테니

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }

        // 참고로 위에 출력하는 것은 공부하기 위한 용도임
        // 실제 테스트케이스 작성할때는 출력하는 코드를 작성하는 방법은 좋지 않음
        // 테스트를 시스템이 통과 실패를 결정하게 작성하는게 중요함
    }

    // 모든 객체의 가장 root 부모인 Object를 이용해 모든 타입을 조회해보자
    @Test
    @DisplayName("부모 타입으로 모두 조회 - Object")
    void findAllBeanObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        // Object 객체 타입으로 빈 조회

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig { // 이번 테스트에서 사용할 config 관련 클래스 만듬
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        // 궁금한점 어차피 반환을 구체 클래스인 RateDiscountPolicy()를 반환할 것인데
        // 왜 rateDiscountPolicy 메서드의 반환형을 RateDiscountPolicy가 아닌
        // 인터페이스인 DiscountPolicy로 지정하지??
        // -> 물론 반환형을 실제 반환할 구체 클래스형으로 지정해도 됨..
        // 하지만 개발 및 설계를 할때 역할과 구현을 쪼갠것을 확실히 알기위해
        // 실제 함수의 반환값이 구현에 해당해도 메서드의 반환형 선언을 역할로 두는 것이 이해하기 쉽기 때문

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
