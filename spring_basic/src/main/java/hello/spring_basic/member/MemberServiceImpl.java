package hello.spring_basic.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    // new MemoryMemberRepository() 지움
    // memberRepository의 구현체 선택을 여기서 바로 하지 말자 -> DIP 꺠트리기 때문
    // 즉 인터페이스에만 의존하도록 변경 (구체 클래스에 의존 하지 않음)

    // MemberServiceImpl의 생성자를 만듬 -> 생성자를 통해 memberRepository의 구현체가 무엇이 들어갈지 선택
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 자 이렇게 코드를 고치고 나니 MemberServiceImpl에 MemoryMemberRepository에 대한 부분이 없다.
    // 오로지 MemberRepository라는 인터페이스만 존재한다.
    // 즉 추상화만 의존한다. -> 드디어 DIP를 지켰다.
    // 구체적인것에 대한것은 MemberServiceImpl은 전혀 모른다.
    // 누군가가 memberRepository에 MemoryMemberRepository를 넣어줄지, DBMemberRepository를 넣어줄지 모른다.

    // 구체적인 것에 대한것은 생성자를 통해 밖에서(AppConfig) 생성해서 넣어줌
    // 즉 생성자를 통해 객체가 (new MemoryMemberRepository())가 생성되어 들어감
    // -> "생성자 주입" 이라고 함.

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
