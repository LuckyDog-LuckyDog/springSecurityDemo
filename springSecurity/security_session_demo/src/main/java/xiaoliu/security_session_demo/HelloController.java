package xiaoliu.security_session_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description
 *
 * @author:xiaoLiu
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "这是接口";
    }

    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request) {
        return "这是成功的回调地址";
    }

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "xiaoliu");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }
}
