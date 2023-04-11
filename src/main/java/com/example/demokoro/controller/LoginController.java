package com.example.demokoro.controller;

import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.jwt.JwtTokenProvider;
import com.example.demokoro.payload.LoginRequest;
import com.example.demokoro.payload.LoginResponse;
import com.example.demokoro.payload.RandomStuff;
import com.example.demokoro.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@RestController
@RequestMapping
@CrossOrigin
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    //kiểm tra access token có hợp lệ, còn hạn sử dụng hay không
    @GetMapping("/auth/check-token")
    public ResponseEntity<ResponseObject> checkToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        // Kiểm tra header Authorization
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Lấy chuỗi token từ header Authorization
        String token = header.substring(7);

        if(tokenProvider.validateToken(token)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Access token successfully", token));
        }else{
            return ResponseEntity.status(400).body(new ResponseObject("error", "Access token field", null));
        }
    }


    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

    @GetMapping (value="/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
