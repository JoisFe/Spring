package hello.spring_basic.member;

public interface MemberService {

    void join (Member member); //회원 가입 메서드 선언

    Member findMember(Long memberId); // 회원 조회 메서드 선언
}
