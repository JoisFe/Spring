package hello.spring_basic.member;

public interface MemberService { //회원 서비스 구현

    void join (Member member); //회원 가입

    Member findMember(Long memberId); // 회원 조
}
