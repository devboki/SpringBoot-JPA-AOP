package hello.hellospring.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//콜백 메서드
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		//1. System.out.println("result = " + (result == member)); // true
		//2. Assertions.assertEquals(member, result); //출력되는 것은 없지만 save() test 정상적으로 실행됨
		assertThat(member).isEqualTo(result); //3. result -> null 로 실행하면 error 발생
	}

	@Test
	public void findByName() { 
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	//개별적으로 테스트 했을 때는 에러 X 전체 테스트 실행시 에러 발생 -> 테스트 순서는 랜덤. findAll()이 먼저 실행될 시 저장된 데이터가 있기 때문에 에러 발생하는 것. 
	//	그래서 테스트가 끝날 때마다 repository를 깨끗하게 지워줘야 함.
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2); //3일 경우 fail

	}
}
