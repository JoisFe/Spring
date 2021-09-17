package hello.spring_basic.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원 가입 메서드 정의

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    /*
    save메서드 호출시 다형성에 의해 인터페이스인 MemberRepository클래스가 아닌 MemoryMemberRepository 클래스에 있는 save함수를 호출한다.
    */

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    } // 회원 조회 메서드 정의

    // 인터페이스에서 선언한 메서드를 구체적으로 메서드 정의 -> 구현
}
