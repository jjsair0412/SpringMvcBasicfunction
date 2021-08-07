package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          /** locale 정보 조회 **/
                          Locale locale,
                          /**  헤더 전부다 조회
                               MultiValueMap은 하나의 키에 여러가지 값을 받을 수 있음

                               MultiValueMap<String, String> map = new LinkedMultiValueMap();
                               map.add("keyA", "value1");
                               map.add("keyA", "value2");
                               //[value1,value2]
                               List<String> values = map.get("keyA");

                               **/
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          /** 개별 헤더 조회 **/
                          @RequestHeader("host") String host,
                          /** 쿠키 조회 **/
                          @CookieValue(value = "myCookie", required = false) String cookie
    ){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
