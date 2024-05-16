package kr.or.nextit.springmvc.login;

import kr.or.nextit.springmvc.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService service;

    @GetMapping("/login")
    public String loginView() {
        return "common/login";
    }

    @PostMapping("/login")
    public String login(LoginRequest login, HttpSession session, Model model) {
        Member member = service.findMember(login);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:/";
        }
        model.addAttribute("msg", "로그인 실패");
        return "common/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션의 모든 데이터 지우기
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/ajaxLogin")
    @ResponseBody
    public Map<String, String> ajaxLogin(@RequestBody LoginRequest login, HttpSession session) {
        // 성공할 경우 {"msg": "success"}
        // 실패할 경우 {"msg": "failure"}
        log.debug("login: {}", login);
        HashMap<String, String> map = new HashMap<>();
        Member member = service.findMember(login);
        if (member != null) {
            map.put("msg", "success");
            session.setAttribute("member", member);
        } else {
            map.put("msg", "failure");
        }
        return map;
    }

}
