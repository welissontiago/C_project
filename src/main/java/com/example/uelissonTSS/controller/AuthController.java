package com.example.uelissonTSS.controller;

import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.entities.dto.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }
}
