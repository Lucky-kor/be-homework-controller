package com.springboot.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.Member;
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

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping(value = "/{member-id}")
    public ResponseEntity updateMember(@RequestParam("phone") String phone,
                                        @PathVariable ("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);
        if (members.containsKey(memberId)) {
            members.get(memberId).put("phone", phone);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // not implementation
        return new ResponseEntity<>(members.get(memberId), HttpStatus.OK);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    @DeleteMapping(value = "/{member-id}") //언더바 못 씀
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        System.out.println("# remove memberId: " + memberId);
        if (members.containsKey(memberId)) {
            members.remove(memberId);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // not implementation
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
