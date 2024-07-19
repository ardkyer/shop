package com.baeksoo.shop.comment;

import com.baeksoo.shop.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByParentId(long a);
}