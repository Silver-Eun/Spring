package hello.HelloSpring.service;

import hello.HelloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName().isEqualTo(findMember.getName()));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}