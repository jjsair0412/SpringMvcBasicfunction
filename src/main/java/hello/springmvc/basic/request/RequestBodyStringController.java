package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {


    /**
     * 메시지 바디에 값을 넣어서 전달하는 코드
     * */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);
        response.getWriter().write("ok");
    }

    /**
     * 스프링 mvc에서는 InputStream과 Writer를 그대로 파라미터에 넣는것을 지원한다.
     *
     *  */

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity를 파라미터값으로 가지고올수 있다.
     * HttpEntity는 메시지 바디 정보를 직접 조회할 수 있다.
     *
     * 요청 파라미터를 조회하는기능과는 전혀관계가 없다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        /**
         * http 메세지에있는 body를 그대로 꺼내올 수 있다.
         */
        String body = httpEntity.getBody();

        log.info("messageBody = {}",body);

        /**
         * http 메세지 바디에 값을 그대로 return해줄수 있다.
         */
        return new HttpEntity<>("ok");
    }


    /**
     * @RequestBody 어노테이션을 이용해서 메시지 바디를 그냥 받아올수있다..
     * @ResponseBody 어노테이션을 활용해서 그냥 ok를 보내줄수있다..
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}",messageBody);
        return "ok";
    }

}
