package hello.HelloSpring.controller;

import hello.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
