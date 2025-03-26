package com.tvb.api.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TestController {
    @PostMapping("/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<?> testg() {
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
