package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIntroduction {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntroduction.class, args);
	}

	/**
	 * 기본적으로 @SpringBootApplication 클래스가 해당되는 패키지의 하위 클래스들만 자동 컴포넌트 스캔의 대상이 된다.
	 * 적용되는 패키지 레벨을 변경하고 싶을 경우에는 별도의 설정을 해주어야 한다.
	 */

}
