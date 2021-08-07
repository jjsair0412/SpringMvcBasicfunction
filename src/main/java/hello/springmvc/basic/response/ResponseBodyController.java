package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
/**
 @ResponseBody
 클래스에 ResponseBody를 넣어주면
 전체 메서드에 다 적용된다.
 ResponseBody와 Controller를 합친게바로
 @RestController이다.
 **/
public class ResponseBodyController {

    /**
     * 문자 자체를 HttpMessage body에 넣어서 보내는 방법
     * responseBodyV1 ~ responseBodyV3
     *
     **/
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response)throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }
    /**
     * json을 HttpMessage body에 넣어서 보내는 방법
     * responseBodyJsonV1 ~ responseBodyJsonV3
     *
     * v1은 status를 보낼 수 있는데에 반해
     * v2는 status를 보낼 수 없다.
     * 그래서 @ResponseStatus를 사용한다.
     **/
    @GetMapping("/response-body-json-v1")
    @ResponseBody
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
