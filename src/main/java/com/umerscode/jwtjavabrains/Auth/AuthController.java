package com.umerscode.jwtjavabrains.Auth;

import com.umerscode.jwtjavabrains.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;
    private final JwtUtils jwtUtils;

    @GetMapping("/home")
    public String home(){
        return "WELCOME HOME";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception{

        System.out.println("HELLO");
        AppUser userDetails = (AppUser) userDetailService.loadUserByUsername(authRequest.getEmail());
        System.out.println(userDetails.getUsername());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        String jwt = jwtUtils.generateJwt(userDetails);

        return  ResponseEntity.ok().body(new AuthResponse(jwt));
    }
}
