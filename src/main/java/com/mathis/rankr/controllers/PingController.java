package com.mathis.rankr.controllers;

import com.mathis.rankr.models.utils.CustomResponse;
import com.mathis.rankr.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<CustomResponse> ping() {
        return ResponseUtil.build(HttpStatus.OK, "pong");
    }
}
