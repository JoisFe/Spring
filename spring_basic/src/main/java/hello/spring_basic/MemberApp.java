package hello.spring_basic;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService(); // memberService 필요시 appConfig에서 인터페이스 만듬
        // memberService에는 MemberServiceImpl 객체인데 MemoryMemberRepository()를 사용하는 것을 주입 (AppConfig에 있음)

        // 기존에 main 메서드에서 MemberServiceImpl을 직접 생성했었다. -> DIP 어김 따라서 제거

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 환경설정 정보를 가지고 Spring이 AppConfig 클래스 안에 있는 (@Bean 아래 있는 것들은 Spring Container 안에 등록)

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 매개변수 (name, 타입)
        // 스프링 컨테이너에 등록한 AppConfig에 있는 memberService 가져오고 싶을때
        // 기본적으로 name은 메서드 이름

        Member member = new Member(1L, "memberA", Grade.VIP);// 새로운 Member의 객체 member 생성
        //이름은 memberA, 등급은 VIP

        memberService.join(member); // 새로운 member를 등록 (회원가입)

        Member findMember = memberService.findMember(1L);//위 사람이 제대로 등록(회원 가입) 되었는지 확인해보자.

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName()); //회원가입이 잘 되었다면 member.getName()과 findMember.getName()이 같은 출력을 내놓아야야

        //똑같은 "memberA"를 출력함
    }
}
