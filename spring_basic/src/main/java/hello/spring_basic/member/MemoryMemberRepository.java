package hello.spring_basic.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    //implements : 부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩 해서 사

    private static Map<Long, Member> store = new HashMap<>(); // 데이터 저장하기 위해 해시맵에 데이터 저장

    // HashMap은 사실 동시성 문제가 발생할 수 있으므로 그런 경우에는 ConcurrentHashMap 사용하면 된다.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    } //회원 저장하는 메서드 정의

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    } //회원 ID로 회원 찾는 메서드 정의

    //인터페이스에서 선언한 메서드를 구체적으로 메서드 정의 -> 구현
}
