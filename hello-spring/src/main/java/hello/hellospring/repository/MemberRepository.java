package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository { //저장소

	Member save(Member member);
	Optional<Member> findById(Long id); //java 8
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
