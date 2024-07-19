package com.baeksoo.shop.comment;

import com.baeksoo.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    String postComment(@RequestParam String content,
                       @RequestParam Long parent,
                       Authentication auth
    ){
        CustomUser user = (CustomUser) auth.getPrincipal();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUsername(user.getUsername());
        comment.setParentId(parent);
        commentRepository.save(comment);

        return "redirect:/detail/" + parent;
    }

}
