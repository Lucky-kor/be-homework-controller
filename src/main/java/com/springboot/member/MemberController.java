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
    //members 는 <long ,MAP>
    //Map 이 member <Sting,Object> 인 것
    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        //이렇게 키 밸류 묶어있는 것이 엔트리라고 한다 4개의 엔트리로 구성되어있다.
        //members 는 { 1L : { memberId = 1
                            //email = "hgd@gmail.com"
                            //name = "홍길동 ....  이렇게 폰넘버까지 들어있는 것
//        : { memberId = 1
            //email = "hgd@gmail.com"
            //name = "홍길동 ....  이렇게 폰넘버까지 들어있는 것 여기 1L 뒤까지가 맵
        // 이렇게 맵을 가져와야한다


        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

//객체가 맵 members -> 1 : member1 key value
    //json 으로 보면 { "1" : { name:---
    //email ---- phone --- } 이런식임 key value


    //   /v1/members/1  1이 pk ?id=1 인 것
//    @PatchMapping("/{member-id}")
    //ResponseEntity 는 위에 메서드 이름과 보통 이름 맞춘다.
                                //filter sort  나중에 언제 쓸지 구분한다 pathvariable 과
                                //특정 값을 조회할 때 쓰는데 특정 조건으로 필터링할 때 ,정렬할 때 많이 쓴다
    //특정한 곳에 넣어줘야하기 때문에 long memberId 에 넣는 것  -로 구분자 넣는 이유 uri 에는 언더바,대문자 안된다.
    //@RequestParam 으로 바꿀 값 가져오지만 우리는 이제 잘 안 쓸 것..Requestbody 로 쓸 것 이다
    @PatchMapping("/{member-id}")
    // 이 url 에 들어오는 것은 내가 다 처리하겠다

    public ResponseEntity patchNum(@PathVariable("member-id")long memberId,
    @RequestParam("phone") String phone) {
        members.get(memberId).replace("phone",phone);
        //전달받은 MemberId에 해당하는 맴버가 존재하는지 확인
        // 있다면 맴버 정보 조회
        //"phone" http 요청에서 "phone" 키를 가져와서 그 값을 String phone 에 넣어주는 것
        if(!members.containsKey(memberId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Map<String, Object> currentMember;
        currentMember = members.get(memberId);
        //정보 있다면 전화번호 수정
//        currentMember.replace("phone", phone);
        currentMember.put("phone", phone);

        // put과 repalce 차이는 응답객체
        //"phone" 은 위에 넣을 공간 / 뒤에 phone 은 내가 받을 벨류 즉 들어올 밸류 인 것
        //cuurentMember 은 타입은 맵이다
        //맵에서 넣고 수정하는 것은 put
        return new ResponseEntity<> (currentMember, HttpStatus.CREATED);

        //수정끝났으면 보내줘야함
        //OK는 모든 경우
        //CREATE 는 생성이 잘 되었습니다.

    }
    //이메일 같은 것만 삭제한다고 볼 때
    //삭제할 때 특정 값만 가져오는 것이 아니라
    //아예 멤버아이디 전체를 가져온다 음 삭제해야한다
    //회원탈퇴할때도 로그인 부터 한다.

//삭제 대상이 있는 지 확인한다
//삭제 대상을 가져온다
    //대상을 삭제한다
    //"/{member-id}" 이게 주소값에 오는 아이디 값
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteUser (@PathVariable("member-id")long memberId) {
//        Map<String, Object> deleteMember;
//        deleteMember = members.get(memberId);
        if (!members.containsKey(memberId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);  //아까 위는 값이 없던거고 얘는 낫 파운드 처리
        } else {
            members.remove(memberId);
            //맵 - remove 쓰는 것
            //remove 로 키 값 가져오면 안에 엔트리 들이 다 삭제된다 .
            //멤버 아이디로 받아야한다. 멤버 전체 삭제한는 거니까
            //삭제 후 조회하면 404 낫 파운드 나와여한다
            //삭제 돼서 없는 거니까
            return null;
        }
    }



} //여기가 끝
    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현  패치

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현 delete  핸들러 메서드 직접 만들어야함


