package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");

        /**
         * Controller에서 리턴값으로 문자를 반환하면 ViewResolver가 해당하는 화면을 찾아서 처리한다.
         * 스프링부트 템플릿엔진 기본 viewName 매핑
         * resources:templates/{ViewName}.html
         */
        return "hello"; // resources/templates/ hello.html 리턴한 이름과 동일한 파일을 찾아서 Thymeleaf 템플릿 엔진 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http response body에 문자 내용을 직접 반환. viewResolver 대신 HttpMessageConverter가 동작한다.
    public String helloString(@RequestParam("name") String name) {
        // StringConverter가 동작함 (기본 문자 처리 : StringHttpMessageConverter)
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // JsonConverter가 동작함 (기본 객체 처리 : MappingJackson2HttpMessageConverter)
        return hello; // (default) json 타입으로 화면에 리턴됨. (ex, {"name":"value"})
    }

    static class Hello {
        private String name;

        // java bean 규약(표준방식), property 접근방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
