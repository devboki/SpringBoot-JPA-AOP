package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	/* 정적 컨텐츠 */
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!!");
		return "hello";
	}
	
	/* MVC */
	@GetMapping("hello-mvc") //hello-mvc?name=spring
	public String helloMvc(@RequestParam("name") String name, Model model) { 
		model.addAttribute("name", name);
							//key, name
		return "hello-template";
	}
	
	/* API : string */
	@GetMapping("hello-string")
	@ResponseBody //http의 body에 return 값을 직접 넣어주겠다 => view template X data 그대로 출력
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}
	
	/* API : json */
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello {
		private String name;

		//java bean 규약. 프로퍼티 접근 방식
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
