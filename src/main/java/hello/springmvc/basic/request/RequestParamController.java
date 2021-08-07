package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * HttpServletRequest를 이용해서 받아오는 방법
     * */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @ResponseBody를 추가해주면 @RestController와 같이 view 이름이 아닌 String을
     * 반환할 수 있다.
     *
     * RequestParam을 이용해서 Form값이나 uri파라미터값을 바로 받아오는 방법
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberA){
        log.info("username ={}, age ={}",memberName, memberA);

        return "ok";
    }

    /**
     * 파라미터 이름과 변수명이 동일하다면 @RequestParam의 값을 없앨수있다.
     * */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int memberA){
        log.info("username ={}, age ={}",username, memberA);

        return "ok";
    }

    /**
    요청파라미터와 변수명이 같다면 @RequestParam 어노테이션마저 생략이 가능하다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int memberA){
        log.info("username ={}, age ={}",username, memberA);

        return "ok";
    }

    /**
     * required를 사용해서 필수로 들어와야하는값과 아닌값을 구분할 수 있다.
     * required가 true라면 무조건 값이 들어와야하고
     * false라면 값이 들어오지 않아도 오류가 발생하지 않는다.
     *
     * 기본은 true로 설정되어있다.
     *
     * 그런데 int값에 null을줄수는 없다. int는 객체형이 아니기 때문이다.
     * 따라서 Integer로 바꾸어야 한다.
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer memberA){
        log.info("username ={}, age ={}",username, memberA);

        return "ok";
    }


    /**
     * defaultValue값을 설정해주어서 아무런 값이 들어오지 않았을 경우
     * 넣을 값을 설정해 줄 수 있다.
     *
     * 또한 빈 문자가 들어올 경우에도 설정한 디폴트값이 들어간다.
     * null과 " "얘는 다르다.
     * */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int memberA){
        log.info("username ={}, age ={}",username, memberA);

        return "ok";
    }

    /**
     * 모든 요청 파라미터를 한꺼번에 받고 싶다면
     * Map을 이용해서 받으면 된다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttribute 어노테이션을 활용해서 HelloData 객체 내부의 변수들을 자동으로
     * 다 들어가게끔할 수 있다.
     *
     * HelloData객체 안에 있는 변수인 String형의 username과 int형의 age가
     * Form에서 보내는 파라미터값과 동일하기때문에
     * 자동으로 set을 해준다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 어노테이션도 생략이 가능하다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
