package com.baeksoo.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String register(){
        return "register.html";
    }

    @PostMapping("/member")
    String addMember(String username, String password, String displayName){
        Member member = new Member();
        member.setUsername(username);
        member.setDisplayName(displayName);
        var hash = new BCryptPasswordEncoder().encode(password);
        member.setPassword(hash);
        memberRepository.save(member);
        return "redirect:/list";
    }
}
