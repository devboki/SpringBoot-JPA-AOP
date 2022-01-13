package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

	//MemberService memberService = new MemberService();
	//MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	//다른 인스턴스 사용. 같은 인스턴스를 사용하려면 20-26 라인으로 수정
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("회원1");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("회원1");
		
		Member member2 = new Member();
		member2.setName("회원1");		
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
											//이 예외가 터져야 함. NullPointerException.class 실행시 테스트 실패
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		/*
		 * try { memberService.join(member2); fail(); } catch (IllegalStateException e)
		 * { assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //예외 message 같으면 성공
		 * }
		 */
		
		//then			
	}
	
	
	@Test
	void 전체회원조회() {
		//given
		
		//when
				
		//then		
	}
	
	@Test
	void 회원조회() {
		//given
		
		//when
				
		//then		
		
	}
}
