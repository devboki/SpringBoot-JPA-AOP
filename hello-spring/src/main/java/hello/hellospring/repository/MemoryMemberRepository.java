package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hellospring.domain.Member;


public class MemoryMemberRepository implements MemberRepository { //구현체

	//동시성 문제가 고려되어 있지 않음. 가장 단순한 예제를 사용하기 위해 작성
	//실무에서는 ConcurrentHashMap, AtomicLong 고려
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}
}
