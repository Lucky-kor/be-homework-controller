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
        // key value를 묶은 게 엔트리.
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현 patch
    @PatchMapping("/{member-id}")
    // 행위가 일치하는 곳에서 메서드를 실행함.
    public ResponseEntity patchMember (
            @RequestParam("phone") String phone,
            @PathVariable long memberId ) {
        // 회원 정보를 수정할 수 있어야 함.
        // 여기에 추가로 주소를 넣을 수 있음.
        Map<String, Object> currentUser;
        currentUser = members.get(memberId);
        // 애초에 null이 들어오는 상황을 피해야 함.

        if(currentUser.containsKey(memberId)) {
            currentUser.put("phone", phone);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        members.get(memberId).put("phone", phone);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember (
            @PathVariable String memberId ) {
        // 추가로 회원 정보를 삭제할 수 있어야 함.
        Map<String, Object> currentUser;
        if(members.containsKey(memberId)) {
            members.remove(memberId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
