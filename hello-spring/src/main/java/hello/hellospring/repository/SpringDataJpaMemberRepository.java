package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	@Override
	Optional<Member> findByName(String name);
	//select m from Member m where m.name = ? 규칙에 따라 jpql 실행
	//findByAndId(String name, Long id); 가능
}
