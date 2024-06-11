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

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현 patch
    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현 delete
    // memberId 는 URI 경로에 포함: /v1/members/1

    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") Long memberId,
            @RequestParam String phone){

       // if(members == null) return new ResponseEntity(HttpStatus.NOT_FOUND); 이렇게 쓰기 보단 아래처럼 예외 처리.
        if(!members.containsKey(memberId)) return new ResponseEntity(HttpStatus.NOT_FOUND);

        Map<String, Object> member = members.get(memberId);
        member.put("phone", phone);
     //   member.replace("phone", phone); 를 사용해도 됨.

        return new ResponseEntity(member,HttpStatus.OK); // OK 대신 CREATED 사용
    }
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") Long memberId){
        if(!members.containsKey(memberId)) return new ResponseEntity(HttpStatus.NOT_FOUND);

        members.remove(memberId);
        return new ResponseEntity(members.get(memberId),HttpStatus.OK);
    }
}
