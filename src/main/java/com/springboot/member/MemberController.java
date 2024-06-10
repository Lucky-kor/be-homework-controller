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

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestParam("phone") String phone) {
        Map<String, Object> map = new HashMap<>();
        map = members.get(memberId);
        map.put("phone", phone);

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        members.remove(memberId);
        System.out.println(members.get(1L));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현

}