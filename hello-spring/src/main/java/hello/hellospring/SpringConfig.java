package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	
	/* Spring data JPA */
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

//	private final DataSource dataSource;
//	
////@PersistenceContext 혹은
//	private final EntityManager em;
//	
//	@Autowired //하나일 때 생략 가능
//	public SpringConfig(DataSource dataSource, EntityManager em) {
//		this.dataSource = dataSource;
//		this.em = em;
//		}
	
	/* 자바 코드로 spring bean 직접 등록 */
	@Bean 
	public MemberService memberService() {
		//return new MemberService(memberRepository());
		return new MemberService(memberRepository);
	}
	
	/* AOP. 스프링빈 등록 혹은 aop class에 @Component 선언 */
//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
	
	
//	@Bean
//	public MemberRepository memberRepository() {
//		//return new MemoryMemberRepository(); //구현체 new
//		//return new JdbcMemberRepository(dataSource);
//		//return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
	
	
}
