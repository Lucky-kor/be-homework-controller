package com.springboot.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

    @PostMapping("/{member_id}")
    public ResponseEntity getMember(@PathVariable("member_id") Long memberId) {
        Map<String, Object> member = members.get(memberId);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    //put
    @PatchMapping("/{member_id}")
    public ResponseEntity putMember(@PathVariable("member_id") Long member_id,
                                    @RequestParam(value = "phone",required = false) String phone,
                                    @RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "email",required = false) String email) {

        Map<String, Object> member = members.get(member_id);
        if (phone != null) {member.put("phone", phone);}
        if (name != null) {member.put("name", name);}
        if (email != null) {member.put("email", email);}

        return new ResponseEntity(member, HttpStatus.OK);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    //delete
    @DeleteMapping("/{member_id}")
    public ResponseEntity deleteMember(@PathVariable("member_id") Long memberId) {
        members.remove(memberId);
        Map<String, Object> member = members.get(memberId);

        return new ResponseEntity(member, HttpStatus.NO_CONTENT);
    }
}
