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
    // members는 회원 정보를 저장하는 맵입니다.
    private final Map<Long, Map<String, Object>> members = new HashMap<>();
    // Long 타입의 회원 ID를 키로 사용하고, 회원 정보를 담은 Map<String, Object>를 값으로 사용합니다.


    // 초기화 메서드, 서버 시작 시 한 명의 회원을 등록합니다.
    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");
        // 하나의 쌍을 Entry 라 부른다.
        members.put(memberId, member1);
    }

    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    // 특정 회원의 전화번호를 수정합니다.
    // pathvariable 언더바 대문자 x
    @PatchMapping("/{memberId}")
    public ResponseEntity change(@PathVariable("memberId") long memberId,
                                 @RequestParam("phone") Object phone) {
        // 회원이 존재하는지 확인
        if (members.containsKey(memberId)) {
            // 전화번호 수정
            members.get(memberId).put("phone", phone);
            // 수정된 회원 정보를 반환
            return new ResponseEntity<>(members.get(memberId), HttpStatus.OK);
        }
        // 회원이 존재하지 않으면 404 응답
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    // 특정 회원의 정보를 삭제합니다.
    @DeleteMapping("/{memberId}")
    public ResponseEntity delete(@PathVariable("memberId") long memberId) {
        // 회원이 존재하는지 확인
        if (members.containsKey(memberId)) {
            // 회원 삭제
            members.remove(memberId);
            // 성공적으로 삭제되면 200 응답
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // 회원이 존재하지 않으면 204 응답
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
