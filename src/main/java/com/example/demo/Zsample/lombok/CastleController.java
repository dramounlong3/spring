package com.example.demo.Zsample.lombok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastleController {

    @GetMapping("/lombok")
    public ResponseEntity<?> castle() {
        Castle castle = new Castle();
        castle.setId(90);
        castle.setName("Eastin");
        return ResponseEntity.status(200).body(castle);
    }

}
