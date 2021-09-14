package hello.spring_basic.member;

public interface MemberRepository {  //인터페이스
    void save (Member member); // 회원을 저장하는 메서드

    Member findById(Long memberId); //회원의 아이디로 회원을 찾는 메서드
}
