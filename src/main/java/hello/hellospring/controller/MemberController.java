package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/MemberController")
//@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // new로 하면 멤버 컨트롤러 말고 다른 여러 컨트롤러들이 멤버 서비스를 가져다 쓸 수 있다. -> 문제
    // 따라서 이것보다는 스프링 컨테이너에 등록을 하고 쓰는 것이 좋음.(딱 하나만 등록)
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form){  // create
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){    // read
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //member리스트르리 model에 담아서 화면에 넘김.
        return "members/memberList";
    }



}
