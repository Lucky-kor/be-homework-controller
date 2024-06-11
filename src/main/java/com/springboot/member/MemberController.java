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
    // Map의 key: Long, value: Map<String, Object>. Map을 한 명의 멤버라고 할 수 있다. Map안의 Map. 실무에서는 Object 안 써
    // key: String, value: Object
    // put은 파라미터 두개를 받는다. 자바의 Map replace가 있나요

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
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestParam String phone) { // phone 이름으로 값을 찾는다

        if (!members.containsKey(memberId)) return new ResponseEntity(HttpStatus.NOT_FOUND);

        Map<String, Object> member =  members.get(memberId); // 이 값이 null인거야 그래서 오류

        if(member == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        member.put("phone", phone);
        return new ResponseEntity<>(member ,HttpStatus.CREATED);


//        members.get(memberId).replace("phone", phone);
//        return new ResponseEntity<>(members.get(memberId),HttpStatus.CREATED);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {

        if(!members.containsKey(memberId)) return new ResponseEntity(HttpStatus.NOT_FOUND);

        members.remove(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
    // members 객체가 map으로 되어있다
    // members -> 1:member1
    // { '1': { name: 'Bohyun'; email: 'bohyun@gmail.com', phone: '010-1122-3344' } }
    // 수정해야되는 키는 'phone' 키, "010-1234-5678"
    // 그리고 반환해야해. 소괄호 안에 Status 앞에.

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현 수정 PATCH
    // http 요청이 들어오면 휴대폰을 010-1111-2222로 변경해야함. init 데이터를 바꿔야함 PATCH 요청을 보냈을 떄
    // member id 가 key. 수정 될 때 responseEntity를 반환


    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현 DELETE
    // memberId는 URI 경로에 포함 되어야 합니다.-> path variable로 받야야해


