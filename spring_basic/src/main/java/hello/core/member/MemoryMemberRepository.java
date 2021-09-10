package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    //implements : 부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩 해서 사

    private static Map<Long, Member> store = new HashMap<>(); // 데이터 저장하기 위해 해시맵에 데이터 저장

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
