package com.springboot.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! -------------------//
    // 1. 커피 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping("/{coffeeId}")
    // 행위가 일치하는 곳에서 메서드를 실행함.
    public ResponseEntity patchCoffee (
            @RequestParam("korName") String korName,
            @RequestParam("price") String price,
            @PathVariable long coffeeId ) {
        // 회원 정보를 수정할 수 있어야 함.
        // 여기에 추가로 주소를 넣을 수 있음.
        Map<String, Object> currentCoffee;

        if (coffees.containsKey(coffeeId)) {
            currentCoffee = coffees.get(coffeeId);
            currentCoffee.put("korName", korName);
            currentCoffee.put("price", price);
            return new ResponseEntity<>(currentCoffee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // 2. 커피 정보 삭제를 위한 핸들러 서드 구현
    @DeleteMapping("/{coffeeId}")
    public ResponseEntity deleteMember (
            @PathVariable String coffeeId ) {
        // 추가로 회원 정보를 삭제할 수 있어야 함.
        Map<String, Object> currentCoffee;
        if(coffees.containsKey(coffeeId)) {
            coffees.remove(coffeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
