package com.nic.api;

import com.nic.jwtconfig.JwtService;
import com.nic.model.JwtRequest;
import com.nic.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class NonAuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String hello() {
        return "hello public";
    }

    @GetMapping("hello")
    public String reqHello() {
        return "hello request";
    }

    @PostMapping("authenticate")
    public String authenticate(@RequestBody JwtRequest request) {
        System.out.println("----------- ENTER --------------");
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authenticate.isAuthenticated())
                return jwtService.generateToken(request.getUsername());
            else
                return "Invalid Credential";
        } catch (Exception e) {
            return "Exception : " + e + " : " + e.getStackTrace()[0];
        }
    }
}
