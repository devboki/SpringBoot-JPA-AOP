package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//JPA는 모든 데이터베이스 변경시 트랜잭션 안에서 실행되도록 해야 함
@Transactional
public class MemberService {

	//private final MemberRepository memberRepository = new MemoryMemberRepository();
	//같은 인스턴스를 사용하려면 new가 아닌 외부에서 넣어주기 (라인 13-16)
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/* 회원 가입 */
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		//중복(같은 이름) 회원 X
		Optional<Member> result = memberRepository.findByName(member.getName());
		//null이 아닌 값이 있으면 동작. Optional 때문에 가능
		result.ifPresent(m -> { 
			throw new IllegalStateException("이미 존재하는 회원입니다."); 
		});
	}
	
	/* 전체 회원 조회 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
	
}//end
