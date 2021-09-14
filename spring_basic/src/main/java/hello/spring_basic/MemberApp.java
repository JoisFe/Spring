package hello.spring_basic;

import hello.spring_basic.member.Grade;
import hello.spring_basic.member.Member;
import hello.spring_basic.member.MemberService;
import hello.spring_basic.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl(); // MemberService의 객체 memberservice 생성 (MemberServiceImpl 구현 가지는)
        Member member = new Member(1L, "memberA", Grade.VIP);// 새로운 Member의 객체 member 생성
        //이름은 memberA, 등급은 VIP

        memberService.join(member); // 새로운 member를 등록 (회원가입)

        Member findMember = memberService.findMember(1L);//위 사람이 제대로 등록(회원 가입) 되었는지 확인해보자.

        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName()); //회원가입이 잘 되었다면 member.getName()과 findMember.getName()이 같은 출력을 내놓아야야

        //똑같은 "memberA"를 출력함
    }
}
